package ampos.restaurant.service;

import java.util.List;

import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.dto.BillItemReportDTO;
import ampos.restaurant.domain.dto.TotalBillReportDTO;
import ampos.restaurant.exception.ApplicationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Bill and Bill Item Service
 *
 */
public interface BillService {
    /**
     *  Update bill
     *
     * @param
     * @throws ApplicationException
     */
    BillDTO createBill() throws ApplicationException;

    /**
     * Get bill by Id
     *
     * @param id
     * @return
     * @throws ApplicationException
     */
    BillDTO findBillById( Long id ) throws ApplicationException;

    /**
     * Get list bill items
     *
     * @param pageable
     * @return
     * @throws ApplicationException
     */
    Page<BillDTO> findAllBill( Pageable pageable ) throws ApplicationException;

    /**
     *  Create bill item
     *
     * @param billItemDTO
     * @throws ApplicationException
     */
    BillItemDTO createBillItem( Long billId,  BillItemDTO billItemDTO ) throws ApplicationException;

    /**
     *  Create bill item
     *
     * @param billId: Bill that this Bill Item belongs
     * @param billItemId: id of the Bill Item to be updated
     * @param quantity: update quantity of the Bill Item
     * @throws ApplicationException
     */
    BillItemDTO editBillItem( Long billId, Long billItemId, Integer quantity ) throws ApplicationException;

    /**
     * Delete bill item
     *
     * @param id
     * @throws ApplicationException
     */
    void deleteBillItem( Long id );

    /**
     * Get menu items report

     * @return
     * @throws ApplicationException
     */
    TotalBillReportDTO getBillReport( ) throws ApplicationException;
}
