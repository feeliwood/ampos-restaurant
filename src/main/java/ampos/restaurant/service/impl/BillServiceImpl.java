package ampos.restaurant.service.impl;

import ampos.restaurant.domain.BillItem;
import ampos.restaurant.service.BillService;
import org.springframework.transaction.annotation.Transactional;

import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.mapper.BillItemMapper;
import ampos.restaurant.domain.mapper.BillMapper;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.repository.BillItemRepository;
import ampos.restaurant.repository.BillRepository;
import ampos.restaurant.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Bill Item Service Implmentation
 *
 */
@Service
@Transactional
public class BillServiceImpl implements BillService {

    private final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);

    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final BillItemRepository billItemRepository;
    private final BillItemMapper billItemMapper;

    public BillServiceImpl(BillRepository billRepository, BillItemRepository billItemRepository, BillMapper billMapper, BillItemMapper billItemMapper) {
        this.billRepository = billRepository;
        this.billItemRepository = billItemRepository;
        this.billMapper = billMapper;
        this.billItemMapper = billItemMapper;
    }

    /**
     * Create a new bill
     *
     * @return the persisted entity
     */
    @Override
    public BillDTO createBill(  )  throws ApplicationException {
        log.debug("Request to update Bill ");
        return billMapper.toDto(billRepository.save(new Bill()));
    }

    /**
     * Delete a bill
     *
     * @param id of the menu item to be deleted
     * @return the persisted entity
     */
    @Override
    public void deleteBill( Long id ) {
        log.debug("Request to delete Bill : {}", id);
        billRepository.deleteById( id );
    }

    /**
     *  Get all the bill with pageable information.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BillDTO> findAllBill(Pageable pageable) {
        log.debug("Request to get all BillItems");
        return billRepository.findAll(pageable)
                        .map(billMapper::toDto);
    }

    /**
     *  Get one bill by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BillDTO findBillById(Long id) throws ApplicationException {
        log.debug("Request to get Menu Item : {}", id);
        Bill bill = billRepository.findById(id).orElseThrow( () -> new ApplicationException( Constants.MENU_ITEM_NOT_FOUND ) ) ;
        return billMapper.toDto(bill);
    }

    /**
     *  Save Bill Item to a Bill specify by billId
     *
     * @param billId of the Bill that this Bill Item belongs to
     * @param billItemDTO
     * @throws ApplicationException
     */
    @Override
    public BillItemDTO createBillItem( Long billId, BillItemDTO billItemDTO ) throws ApplicationException {
        log.debug("Request to create new BillItem : {}", billItemDTO);

        billRepository.findById(billId).orElseThrow( () -> new ApplicationException( Constants.BILL_NOT_FOUND ) ) ;
        billItemDTO.setBillId(billId);

        BillItem newBillItem = billItemMapper.toEntity(billItemDTO);
        newBillItem.setId(0);
        newBillItem.setOrderedTime(Instant.now());
        return billItemMapper.toDto( billItemRepository.save(newBillItem));
    }

    /**
     *  Create bill item
     *
     * @param billId: Bill that this Bill Item belongs
     * @param billItemId: id of the Bill Item to be updated
     * @param quantity: update quantity of the Bill Item
     * @throws ApplicationException
     */
    @Override
    public BillItemDTO editBillItem( Long billId, Long billItemId, Integer quantity ) throws ApplicationException {
        if (quantity.intValue() <= 0)
            throw new ApplicationException( Constants.INVALID_QUANTITY_FOR_BILL_ITEM );

        log.debug("Request to edit new BillItem with id : ", billItemId);

        Bill bill = billRepository.findById(billId).orElseThrow( () -> new ApplicationException( Constants.BILL_NOT_FOUND ) ) ;
        BillItem billItem = bill.getBillItems()
                .stream()
                .filter( item -> item.getId() == billItemId.longValue() )
                .findAny()
                .orElseThrow( () -> new ApplicationException( Constants.BILL_ITEM_NOT_FOUND ) ) ;
        billItem.setQuantity(quantity);
        billItem.setOrderedTime(Instant.now());
        billRepository.save(bill);
        return billItemMapper.toDto(billItem);
    }

    /**
     * Delete a bill item
     *
     * @param id of the menu item to be deleted
     * @return the persisted entity
     */
    @Override
    public void deleteBillItem( Long id ) {
        log.debug("Request to delete Bill item : {}", id);
        billItemRepository.deleteById( id );
    }

}
