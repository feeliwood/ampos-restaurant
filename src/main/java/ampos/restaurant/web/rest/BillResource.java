package ampos.restaurant.web.rest;

import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.dto.TotalBillItemReportDTO;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.BillService;
import ampos.restaurant.web.rest.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bill order management controller is used to CRU bill order.
 *
 */
@RestController
public class BillResource {
    public static final String BILL_MAPPING = "/bills";
    public static final String BILL_ITEM_MAPPING = "/bill-items";

    private static final Logger logger = LoggerFactory.getLogger( BillResource.class );

    private BillService billService;

    public BillResource( BillService billService ) {
        this.billService = billService;
    }

    /**
     * CREATE /bills : Create new bill
     * 
     * @return the ResponseEntity with status 200 (OK)
     * @throws ApplicationException
     * @throws URISyntaxException
     */
    @ApiOperation( value = "Create new bill", response = ResponseEntity.class )
    @PostMapping( BILL_MAPPING )
    public ResponseEntity<BillDTO> createBill() throws ApplicationException, URISyntaxException {
        logger.debug( "REST request to create bill" );

        BillDTO result = billService.createBill();
        return ResponseEntity.created(new URI(BILL_MAPPING + result.getId())).body(result);
    }

    /**
     * GET /bills/check : Check all bill items
     *
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation( value = "Check all bill items", response = ResponseEntity.class )
    @GetMapping( BILL_MAPPING + "/check" )
    public ResponseEntity<TotalBillItemReportDTO> checkBillItems() throws ApplicationException {
        logger.debug( "REST request to check all bill items" );
        TotalBillItemReportDTO result = billService.getBillItemReport();
        return new ResponseEntity<>( result, null, HttpStatus.OK );
    }

    /**
     * GET /bills/:id : Get the "id" bill
     *
     * @param id:
     *            the id of the BillItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         BillItemDTO, or with status 404 (Not Found)
     */
    @ApiOperation( value = "Get the bill with id", response = ResponseEntity.class )
    @GetMapping( BILL_MAPPING + "/{id}" )
    public ResponseEntity<BillDTO> getBill( @PathVariable @NotNull Long id ) throws ApplicationException {
        logger.debug( "REST request to get bill : {}", id );
        BillDTO billDTO = billService.findBillById( id );
        return ResponseUtil.wrapOrNotFound( Optional.ofNullable( billDTO ) );
    }

    /**
     * GET /bills : Get all the Bill.
     *
     * @param pageable:
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Bills in
     *         body
     */
    @ApiOperation( value = "Get all bills", response = ResponseEntity.class )
    @GetMapping(BILL_MAPPING)
    public ResponseEntity<Page<BillDTO>> getAllBills(Pageable pageable) throws ApplicationException {
        logger.debug("REST request to get a page of menu items");
        Page<BillDTO> page = billService.findAllBills(pageable);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * POST /bills/{billId}/bill-items : Create a bill item
     *
     * @Param BillItemDTO: contain data about the menu item to be created
     * @throws ApplicationException
     */
    @ApiOperation( value = "Create a bill item", response = ResponseEntity.class )
    @PostMapping( BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING )
    public ResponseEntity<BillItemDTO> createBillItem( @PathVariable @NotNull Long billId, @RequestBody BillItemDTO billItemDTO ) throws ApplicationException, URISyntaxException {
        logger.debug( "REST request to create bill item" );

        BillItemDTO result = billService.createBillItem(billId, billItemDTO);
        return ResponseEntity.created(new URI(BILL_MAPPING + result.getId())).body(result);
    }

    /**
     * PUT /bill/{billId}/item/{billItemId} : Updates an existing bill item.
     *
     * @pathVariable billId: 
     *            bill that this Bill Item belongs
     * @pathVariable billItemId:
     *            id of the Bill Item to be updated
     * @requestBody quantity: update quantity of the Bill Item
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         clientDTO,
     */
    @ApiOperation( value = "Update an existing bill item in a bill", response = ResponseEntity.class )
    @PutMapping( BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING + "/{billItemId}" )
    public ResponseEntity<BillItemDTO> updateBillItem( @PathVariable @NotNull Long billId, @PathVariable @NotNull Long billItemId, @RequestBody Integer quantity ) throws ApplicationException {
        logger.debug( "REST request to update bill item with id  : {}", billItemId );

        BillItemDTO result = billService.updateBillItem(billId, billItemId, quantity);
        return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE /bill/{billId}/item/{billItemId} : Delete the "id" bill item.
     *
     * @param billItemId:
     *            the id of the BillItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     * @throws ApplicationException
     */
    @ApiOperation( value = "Delete a bill item in a bill", response = ResponseEntity.class )
    @DeleteMapping(BILL_MAPPING + "/{billId}" +  BILL_ITEM_MAPPING + "/{billItemId}")
    public ResponseEntity<Void> deleteBillItem(@PathVariable @NotNull  Long billId, @PathVariable @NotNull  Long billItemId) throws ApplicationException {
        logger.debug("REST request to delete menu item : {}", billItemId);
        billService.deleteBillItem(billId, billItemId);
        return ResponseEntity.ok().build();
    }
}
