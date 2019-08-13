package ampos.restaurant.service;

import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.dto.TotalBillItemReportDTO;
import ampos.restaurant.exception.ApplicationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Bill and Bill Item Service
 *
 */
public interface BillService {
    /**
     * Create new bill
     *
     * @param
     * @throws ApplicationException
     */
    BillDTO createBill() throws ApplicationException;

    /**
     * Get bill by Id
     *
     * @param id
     *            : of the bill that this bill item belongs to
     * @return
     * @throws ApplicationException
     */
    BillDTO findBillById( Long id ) throws ApplicationException;

    /**
     * Get list bill items
     *
     * @param pageable
     *            :the pagination information
     * @return
     * @throws ApplicationException
     */
    Page<BillDTO> findAllBills( Pageable pageable ) throws ApplicationException;

    /**
     * Create bill item
     *
     * @param billId
     *            : of the bill that this bill item belongs
     * @param billItemDTO
     *            : the bill item to be persisted
     * @throws ApplicationException
     */
    BillItemDTO createBillItem( Long billId, BillItemDTO billItemDTO ) throws ApplicationException;

    /**
     * Update bill item
     *
     * @param billId
     *            : of the bill that this bill item belongs
     * @param billItemId
     *            : id of the bill item to be updated
     * @param quantity
     *            : update quantity of the bill item
     * @throws ApplicationException
     */
    BillItemDTO updateBillItem( Long billId, Long billItemId, Integer quantity ) throws ApplicationException;

    /**
     * Delete bill item
     *
     * @param billId
     *            : of the bill that this bill item belongs
     * @param billItemId
     *            : id of the bill item to be deleted
     * @throws ApplicationException
     */
    void deleteBillItem( Long billId, Long billItemId ) throws ApplicationException;

    /**
     * Get bill item report
     * 
     * @return
     * @throws ApplicationException
     */
    TotalBillItemReportDTO getBillItemReport() throws ApplicationException;
}