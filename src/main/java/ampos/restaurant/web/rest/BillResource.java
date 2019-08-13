package ampos.restaurant.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import ampos.restaurant.domain.dto.BillDTO;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.domain.dto.TotalBillReportDTO;
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
    private static final String BILL_NAME = "bill";

    private static final Logger logger = LoggerFactory.getLogger( BillResource.class );

    private BillService billService;

    public BillResource( BillService billService ) {
        this.billService = billService;
    }

    /**
     * CREATE /bills : create new bill
     *
     * @Param BillItemDTO: contain data about the bill to be created
     * @throws ApplicationException
     */
    @PostMapping( BILL_MAPPING )
    public ResponseEntity<BillDTO> createBill() throws ApplicationException, URISyntaxException {
        logger.debug( "REST request to create bill:" );

        BillDTO result = billService.createBill();
        return ResponseEntity.created( new URI( BILL_MAPPING + result.getId() ) ).headers( HeaderUtil.createEntityCreationAlert( BILL_NAME, result.getId().toString() ) ).body( result );
    }

    /**
     * GET all bill report
     *
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping( BILL_MAPPING + "/report" )
    public ResponseEntity<TotalBillReportDTO> getAllBillReport() throws ApplicationException {
        logger.debug( "REST request to bill item report: {}" );
        TotalBillReportDTO result = billService.getBillReport();
        return new ResponseEntity<>( result, null, HttpStatus.OK );
    }

    /**
     * GET /bills/:id : get the "id" bill
     *
     * @param id
     *            the id of the BillItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         BillItemDTO, or with status 404 (Not Found)
     */
    @GetMapping( BILL_MAPPING + "/{id}" )
    public ResponseEntity<BillDTO> getBill( @PathVariable @NotNull Long id ) throws ApplicationException {
        logger.debug( "REST request to get Bill : {}", id );
        BillDTO billDTO = billService.findBillById( id );
        return ResponseUtil.wrapOrNotFound( Optional.ofNullable( billDTO ) );
    }

    /**
     * GET /bills : get all the Bill.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Bills in
     *         body
     */
    @GetMapping( BILL_MAPPING )
    public ResponseEntity<Page<BillDTO>> getAllBill( Pageable pageable ) throws ApplicationException {
        logger.debug( "REST request to get a page of Menu Items" );
        Page<BillDTO> page = billService.findAllBill( pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( page, BILL_MAPPING );
        return new ResponseEntity<>( page, headers, HttpStatus.OK );
    }

    /**
     * Create bill item
     *
     * @Param BillItemDTO: contain data about the menu item to be created
     * @throws ApplicationException
     */
    @PostMapping( BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING )
    public ResponseEntity<BillItemDTO> createBillItem( @PathVariable @NotNull Long billId, @RequestBody BillItemDTO billItemDTO ) throws ApplicationException, URISyntaxException {
        logger.debug( "REST request to create bill:" );

        BillItemDTO result = billService.createBillItem( billId, billItemDTO );
        return ResponseEntity.created( new URI( BILL_MAPPING + result.getId() ) )
        					 .headers( HeaderUtil.createEntityCreationAlert( BILL_NAME, result.getId().toString() ) )
        					 .body( result );
    }

    /**
     * PUT /bill/{billId}/item/{billItemId} : Updates an existing bill item.
     *
     * @pathVariable billId: Bill that this Bill Item belongs
     * @pathVariable billItemId: id of the Bill Item to be updated
     * @requestBody quantity: update quantity of the Bill Item
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         clientDTO,
     */
    @PutMapping( BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING + "/{billItemId}" )
    public ResponseEntity<BillItemDTO> updateBillItem( @PathVariable @NotNull Long billId, @PathVariable @NotNull Long billItemId, @RequestBody Integer quantity ) throws ApplicationException {
        logger.debug( "REST request to update Bill Item with id  : {}", billItemId );

        BillItemDTO result = billService.editBillItem( billId, billItemId, quantity );
        return ResponseEntity.ok()
        					 .headers( HeaderUtil.createEntityUpdateAlert( BILL_NAME, result.getId().toString() ) )
        					 .body( result );
    }

    /**
     * DELETE /bill/{billId}/item/{billItemId} : delete the "id" bill item.
     *
     * @param billItemId
     *            the id of the BillItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     * @throws ApplicationException
     */
    @DeleteMapping( BILL_MAPPING + "/{billId}" + BILL_ITEM_MAPPING + "/{billItemId}" )
    public ResponseEntity<Void> deleteBillItem( @PathVariable Long billId, @PathVariable Long billItemId ) throws ApplicationException {
        logger.debug( "REST request to delete menu item : {}", billItemId );
        billService.deleteBillItem( billId, billItemId );
        return ResponseEntity.ok()
        					 .headers( HeaderUtil.createEntityDeletionAlert( BILL_NAME, billItemId.toString() ) )
        					 .build();
    }
}