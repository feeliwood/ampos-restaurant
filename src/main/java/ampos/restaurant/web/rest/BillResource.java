package ampos.restaurant.web.rest;

import ampos.restaurant.domain.dto.*;
import ampos.restaurant.util.Constants;
import ampos.restaurant.util.BillItemRequestView;

import java.net.URISyntaxException;

import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.BillService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping( BillResource.BILL_MAPPING )
public class BillResource extends GenericResource<BillRequestDTO, BillDTO, Long, BillService> {
    public static final String BILL_MAPPING = "/bills";

    public BillResource( BillService billService ) {
        super(billService);
    }

    String getMapping() {
        return BILL_MAPPING;
    }

    @Override
    @PostMapping
    public ResponseEntity<BillDTO> create( @JsonView( BillItemRequestView.Add.class) @RequestBody BillRequestDTO request ) throws ApplicationException, URISyntaxException {
        return super.create( request );
    }

    @Override
    @PutMapping( "/{id}" )
    public ResponseEntity<BillDTO> updateMenuItem( @PathVariable Long id, @JsonView( BillItemRequestView.Edit.class ) @RequestBody BillRequestDTO request) throws ApplicationException {
        return super.updateMenuItem( id, request );
    }

    @Override
    public ResponseEntity<Void> delete( @PathVariable Long id ) throws ApplicationException {
        throw new ApplicationException(Constants.ACTION_NOT_SUPPORTED);
    }

    /**
     * GET /bills/check : Check all bills and bill items
     *
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping( "/checkbill" )
    public ResponseEntity<TotalReportDTO> checkBillAndBillItem() throws ApplicationException {
        TotalReportDTO result = this.service.getBillAndBillItemReport();
        return new ResponseEntity<>( result, null, HttpStatus.OK );
    }
}
