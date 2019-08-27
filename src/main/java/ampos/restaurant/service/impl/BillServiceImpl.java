package ampos.restaurant.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.*;
import ampos.restaurant.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.mapper.BillItemReportMapper;
import ampos.restaurant.domain.mapper.BillMapper;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.repository.BillItemRepository;
import ampos.restaurant.repository.BillRepository;
import ampos.restaurant.service.BillService;
import ampos.restaurant.util.Constants;

/**
 * Bill item service implementation
 *
 */
@Service
@Transactional
public class BillServiceImpl extends GenericServiceImpl<BillRequestDTO, BillDTO, Long, Bill, BillRepository, BillMapper> implements BillService {

    private BillItemRepository billItemRepository;
    private BillItemReportMapper billItemReportMapper;
    private MenuItemRepository menuItemRepository;

    public BillServiceImpl( BillRepository billRepository, BillMapper billMapper, BillItemRepository billItemRepository, BillItemReportMapper billItemReportMapper, MenuItemRepository menuItemRepository ) {
        super(billRepository, billMapper);
        this.billItemRepository = billItemRepository;
        this.billItemReportMapper = billItemReportMapper;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    void mergeExistingAndNewEntity(Bill existingBill, Bill newBill) {
	Set<Long> newBillItemIds = newBill.getBillItems().stream().filter( item -> item.getId() != null).map( BillItem::getId ).collect( Collectors.toSet() );
	if(newBillItemIds.size() > 0)
	    existingBill.getBillItems().removeIf( item -> !newBillItemIds.contains(item.getId()) );
	else
	    existingBill.getBillItems().clear();

	

//        Set<Long> currentBillItemIds = existingBill.getBillItems().stream().map( BillItem::getId ).collect( Collectors.toSet() );
//        if(currentBillItemIds.size() > 0)
//	    newBill.getBillItems().removeIf( item -> ( item.getId() != null && currentBillItemIds.contains(item.getId()) ) );

//	existingBill.getBillItems().clear();
//	existingBill.getBillItems().addAll( newBill.getBillItems() );
    }


    @Override
    public void processBeforeSaving(BillRequestDTO billRequestDTO, Bill bill) {
        if (billRequestDTO.getBillItems().size() <= 0)
            return;

        // Because request only contains MenuItem ID, need to get MenuItem entity from data store to return in the response body
        List<Long> menuIds = bill.getBillItems().stream().map( BillItem::getMenuItem ).map(MenuItem::getId).collect( Collectors.toList());
        List<MenuItem> items = menuItemRepository.findMenusByIdIn( menuIds );
        Map<Long, MenuItem> menuItems = items.stream().collect( Collectors.toMap( MenuItem::getId, menuItem -> menuItem ));

        for(BillItem billItem : bill.getBillItems()) {
            if(billItem.getId() == null) {
		billItem.setOrderedTime( Instant.now() );
		billItem.setBill( bill );
		billItem.setMenuItem( menuItems.get( billItem.getMenuItem().getId() ) );
	    }
        }
    }

    /**
     * Delete bill
     *
     * @param id
     *            : of the bill to be deleted
     * @throws ApplicationException
     */
    @Override
    public void delete( Long id ) throws ApplicationException {
        throw new ApplicationException( Constants.ACTION_NOT_SUPPORTED );
    }

    /**
     * Get bill and bill item report
     *
     * @return
     * @throws ApplicationException
     */
    @Override
    public TotalReportDTO getBillAndBillItemReport() throws ApplicationException {
        TotalBillItemReportDTO totalBillItemReportDTO = new TotalBillItemReportDTO();
        totalBillItemReportDTO.setBillItems( billItemReportMapper.entityToDto(billItemRepository.getAllBillReport()) );

        TotalBillReportDTO totalBillReportDTO = new TotalBillReportDTO();
        totalBillReportDTO.setBills( mapper.entityToDto( repository.findAll()) );
        totalBillReportDTO.setNumberOfBills(totalBillReportDTO.getBills().size());
        totalBillReportDTO.setGrandTotal(totalBillReportDTO.getBills().stream().map( BillDTO::getTotal ).reduce( BigDecimal.ZERO, BigDecimal::add ));

        return new TotalReportDTO(totalBillItemReportDTO, totalBillReportDTO);
    }
}