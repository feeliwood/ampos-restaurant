package ampos.restaurant.web.rest;

import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.BillService;
import ampos.restaurant.web.rest.util.HeaderUtil;
import ampos.restaurant.web.rest.util.PaginationUtil;
import ampos.restaurant.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Bill order management controller is used to CRU bill order.
 * 
 */
@RestController
public class BillResource {
    public static final String BILL_MAPPING = "/bills";
    public static final String BILL_ITEM_MAPPING = "/bill-items";
    private static final String BILL_NAME = "bill";
    private static final String BILL_ITEM_NAME = "bill-item";

    private static final Logger logger = LoggerFactory.getLogger( BillResource.class );

    private BillService billService;

    public BillResource(BillService billService) {
        this.billService = billService;
    }

    /**
     * CREATE  /bills : create new bill
     *
     * @Param BillItemDTO: contain data about the bill to be created
     * @throws ApplicationException
     */
    @PostMapping(BILL_MAPPING)
    public ResponseEntity<BillDTO> createBill() throws ApplicationException, URISyntaxException {
        logger.debug("REST request to create bill:");

        BillDTO result = billService.createBill();
        return ResponseEntity.created(new URI(BILL_MAPPING + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(BILL_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * DELETE  /bills/:id : delete the "id" bill.
     *
     * @param id the id of the bill to be deleted
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping(BILL_MAPPING + "/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        logger.debug("REST request to delete bill : {}", id);
        billService.deleteBill(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(BILL_NAME, id.toString())).build();
    }

    /**
     * GET  /bills/:id : get the "id" bill
     *
     * @param id the id of the BillItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the BillItemDTO, or with status 404 (Not Found)
     */
    @GetMapping(BILL_MAPPING + "/{id}")
    public ResponseEntity<BillDTO> getBill(@PathVariable @NotNull Long id) throws ApplicationException {
        logger.debug("REST request to get Bill : {}", id);
        BillDTO billDTO = billService.findBillById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(billDTO));
    }

    /**
     * GET  /bills : get all the Bill.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Bills in body
     */
    @GetMapping(BILL_MAPPING)
    public ResponseEntity<Page<BillDTO>> getAllBill(Pageable pageable) throws ApplicationException {
        logger.debug("REST request to get a page of Menu Items");
        Page<BillDTO> page = billService.findAllBill(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, BILL_MAPPING);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }


    /**
     * Create bill item
     *
     * @Param BillItemDTO: contain data about the menu item to be created
     * @throws ApplicationException
     */
    @PostMapping(BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING)
    public ResponseEntity<BillItemDTO> createBillItem(@PathVariable @NotNull Long billId, @RequestBody BillItemDTO billItemDTO ) throws ApplicationException, URISyntaxException {
        logger.debug("REST request to create bill:");

        BillItemDTO result = billService.createBillItem(billId, billItemDTO);
        return ResponseEntity.created(new URI(BILL_MAPPING + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(BILL_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /bill/{billId}/item/{billItemId} : Updates an existing menu item.
     *
     * @pathVariable billId: Bill that this Bill Item belongs
     * @pathVariable billItemId: id of the Bill Item to be updated
     * @requestBody quantity: update quantity of the Bill Item
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientDTO,
     */
    @PutMapping(BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING + "/{billItemId}")
    public ResponseEntity<BillItemDTO> updateBillItem(@PathVariable @NotNull  Long billId, @PathVariable @NotNull  Long billItemId, @RequestBody Integer quantity) throws ApplicationException {
        logger.debug("REST request to update Bill Item with id  : {}", billItemId);

        BillItemDTO result = billService.editBillItem(billId, billItemId, quantity);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(BILL_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * DELETE  /billItems/:billItemId : delete the "id" menu item.
     *
     * @param billItemId the id of the BillItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping(BILL_ITEM_MAPPING + "{billItemId}")
    public ResponseEntity<Void> deleteBillItem(@PathVariable Long billItemId) {
        logger.debug("REST request to delete menu item : {}", billItemId);
        billService.deleteBillItem(billItemId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(BILL_NAME, billItemId.toString())).build();
    }
}
