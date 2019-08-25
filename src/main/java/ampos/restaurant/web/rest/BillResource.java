package ampos.restaurant.web.rest;

import ampos.restaurant.domain.dto.*;
import ampos.restaurant.util.Constants;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.BillService;
import ampos.restaurant.web.rest.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ampos.restaurant.web.rest.BillResource.BILL_MAPPING;

@RestController
@RequestMapping( BillResource.BILL_MAPPING )
public class BillResource extends GenericResource<BillRequestDTO, BillDTO, Long, BillService> {
    public static final String BILL_MAPPING = "/bills";
    public static final String BILL_ITEM_MAPPING = "/bill-items";

    public BillResource( BillService billService ) {
        super(billService);
    }

    String getMapping() {
        return BILL_MAPPING;
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
