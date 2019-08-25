package ampos.restaurant.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.*;
import ampos.restaurant.repository.MenuItemRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.mapper.BillItemMapper;
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
    void processExistingEntity(Bill bill) {
	bill.getBillItems().clear();
    }

    @Override
    void processBeforeSaving(BillRequestDTO billRequestDTO, Bill bill) {
        if (billRequestDTO.getBillItems().size() <= 0)
            return;

        // Because request only contains MenuItem ID, need to get MenuItem entity from data store to return in the response body
        List<Long> menuIds = billRequestDTO.getBillItems().stream().map( BillItemRequestDTO::getMenuItemId ).collect( Collectors.toList());
        Map<Long, MenuItem> menuItems = menuItemRepository.findAllById( menuIds ).stream().collect( Collectors.toMap( MenuItem::getId, menuItem -> menuItem ));

        for(BillItem billItem : bill.getBillItems()) {
            billItem.setOrderedTime(Instant.now());
            billItem.setBill(bill);
            billItem.setMenuItem( menuItems.get(billItem.getMenuItem().getId()) );
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
        totalBillItemReportDTO.setBillItems( this.billItemReportMapper.entityToDto(billItemRepository.getAllBillReport()) );

        TotalBillReportDTO totalBillReportDTO = new TotalBillReportDTO();
        totalBillReportDTO.setBills( this.mapper.entityToDto(this.repository.findAll()) );
        totalBillReportDTO.setNumberOfBills(totalBillReportDTO.getBills().size());
        totalBillReportDTO.setGrandTotal(totalBillReportDTO.getBills().stream().map( BillDTO::getTotal ).reduce( BigDecimal.ZERO, BigDecimal::add ));

        return new TotalReportDTO(totalBillItemReportDTO, totalBillReportDTO);
    }
}