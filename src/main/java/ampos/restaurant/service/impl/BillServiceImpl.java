package ampos.restaurant.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.dto.BillItemReportDTO;
import ampos.restaurant.domain.dto.TotalBillItemReportDTO;
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
@Transactional
public class BillServiceImpl implements BillService {

    private final Logger log = LoggerFactory.getLogger( BillServiceImpl.class );

    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final BillItemRepository billItemRepository;
    private final BillItemMapper billItemMapper;
    private final BillItemReportMapper billItemReportMapper;

    public BillServiceImpl( BillRepository billRepository, BillItemRepository billItemRepository, BillMapper billMapper, BillItemMapper billItemMapper, BillItemReportMapper billItemReportMapper ) {
        this.billRepository = billRepository;
        this.billItemRepository = billItemRepository;
        this.billMapper = billMapper;
        this.billItemMapper = billItemMapper;
        this.billItemReportMapper = billItemReportMapper;
    }

    /**
     * Create a new bill
     *
     * @return the persisted entity
     */
    @Override
    public BillDTO createBill() throws ApplicationException {
        log.debug( "Request to update Bill " );
        return billMapper.toDto( billRepository.save( new Bill() ) );
    }

    /**
     * Get all the bill with pageable information.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional( readOnly = true )
    public Page<BillDTO> findAllBills( Pageable pageable ) {
        log.debug( "Request to get all BillItems" );
        return billRepository.findAll( pageable ).map( billMapper::toDto );
    }

    /**
     * Get one bill by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional( readOnly = true )
    public BillDTO findBillById( Long id ) throws ApplicationException {
        log.debug( "Request to get Menu Item : {}", id );
        Bill bill = billRepository.findById( id ).orElseThrow( () -> new ApplicationException( Constants.MENU_ITEM_NOT_FOUND ) );
        return billMapper.toDto( bill );
    }

    /**
     * Create bill item
     *
     * @param billId
     *            of the Bill that this Bill Item belongs to
     * @param billItemDTO
     * @throws ApplicationException
     */
    @Override
    public BillItemDTO createBillItem( Long billId, BillItemDTO billItemDTO ) throws ApplicationException {
        log.debug( "Request to create new BillItem : {}", billItemDTO );

        billRepository.findById( billId ).orElseThrow( () -> new ApplicationException( Constants.BILL_NOT_FOUND ) );
        billItemDTO.setBillId( billId );

        BillItem newBillItem = billItemMapper.toEntity( billItemDTO );
        newBillItem.setId( 0 ); // Set 0 here because we use DTO as creation request also. It is better to create requestVM type or use Json ignore here.
        newBillItem.setOrderedTime( Instant.now() );
        return billItemMapper.toDto( billItemRepository.save( newBillItem ) );
    }

    /**
     * Update bill item
     *
     * @param billId
     *            : Bill that this Bill Item belongs
     * @param billItemId
     *            : id of the Bill Item to be updated
     * @param quantity
     *            : update quantity of the Bill Item
     * @throws ApplicationException
     */
    @Override
    public BillItemDTO updateBillItem( Long billId, Long billItemId, Integer quantity ) throws ApplicationException {
        if ( quantity == null || quantity.intValue() <= 0 )
            throw new ApplicationException( Constants.INVALID_QUANTITY_FOR_BILL_ITEM );

        log.debug( "Request to edit new BillItem with id : ", billItemId );

        Bill bill = billRepository.findById( billId ).orElseThrow( () -> new ApplicationException( Constants.BILL_NOT_FOUND ) );
        BillItem billItem = bill.getBillItems().stream()
                .filter( item -> item.getId() == billItemId.longValue() ).findAny()
                .orElseThrow( () -> new ApplicationException( Constants.BILL_ITEM_NOT_FOUND ) );
        billItem.setQuantity( quantity );
        billItem.setOrderedTime( Instant.now() );
        billRepository.save( bill );
        return billItemMapper.toDto( billItem );
    }

    /**
     * Delete a bill item
     *
     * @param billId
     *            : Bill that this Bill Item belongs
     * @param billItemId
     *            of the menu item to be deleted
     * @return
     */
    @Override
    public void deleteBillItem( Long billId, Long billItemId ) throws ApplicationException {
        log.debug( "Request to delete Bill item : {}", billItemId );
        billItemRepository.deleteBillItemByIdAndBillId( billId, billItemId );
    }

    /**
     * Get bill item report
     *
     * @return
     * @throws ApplicationException
     */
    @Override
    public TotalBillItemReportDTO getBillItemReport() throws ApplicationException {
        TotalBillItemReportDTO totalBillReportDTO = new TotalBillItemReportDTO();
        totalBillReportDTO.setBillItems( billItemRepository.getAllBillReport().stream().map( billItemReportMapper::toDto )
                .collect( Collectors.toList() ) );

        totalBillReportDTO.setTotal( totalBillReportDTO.getBillItems().stream().map( BillItemReportDTO::getSubTotal )
                .reduce( BigDecimal.ZERO, BigDecimal::add ) );
        return totalBillReportDTO;
    }
}