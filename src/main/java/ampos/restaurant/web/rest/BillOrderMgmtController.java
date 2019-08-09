package ampos.restaurant.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bill order management controller is used to CRU bill order.
 * 
 */
@RestController
@RequestMapping( BillOrderMgmtController.CONTROLLER_MAPPING )
public class BillOrderMgmtController {
    public static final String CONTROLLER_MAPPING = "/billOrder";
    private static final Logger logger = LoggerFactory.getLogger( BillOrderMgmtController.class );
//    @Autowired
//    private BillItemService billItemService;
//
//    /**
//     * Create bill Order
//     *
//     * @param billOrderMenu
//     * @throws ApplicationException
//     */
//    @RequestMapping( method = RequestMethod.POST )
//    public void createBillOrder( @RequestBody BillOrderItemInputDTO billOrderMenu ) throws ApplicationException {
//        if ( logger.isDebugEnabled() ) {
//            logger.debug( "Create bill order by Id" );
//        }
//        billItemService.createBillOrder( billOrderMenu );
//    }
//
//    @RequestMapping( method = RequestMethod.GET )
//    public List<BillOrderItemOuputDTO> getAllBillOrderItems() throws ApplicationException {
//        if ( logger.isDebugEnabled() ) {
//            logger.debug( "Getting bill order by Id..." );
//        }
//        return billItemService.getAllBillOrderItems();
//    }
//
//    /**
//     * Get bill orders by ID
//     *
//     * @param id
//     * @return
//     * @throws ApplicationException
//     */
//    @RequestMapping( value = "{id}", method = RequestMethod.GET )
//    public List<BillOrderItemOuputDTO> getBillOrdersByBillId( @PathVariable( "id" ) Long id ) throws ApplicationException {
//        if ( logger.isDebugEnabled() ) {
//            logger.debug( "Getting bill order by Id..." );
//        }
//        return billItemService.getBillOrderByBillId( id );
//    }
//
//    @RequestMapping( value = "/check/{id}", method = RequestMethod.GET )
//    public CheckedBillOrderDTO checkBill( @PathVariable( "id" ) Long id ) throws ApplicationException {
//        if ( logger.isDebugEnabled() ) {
//            logger.debug( "Getting bill order by Id..." );
//        }
//        return billItemService.checkBillOrder( id );
//    }
//
//    /**
//     * Get all bill orders
//     *
//     * @return
//     * @throws ApplicationException
//     */
//    @RequestMapping( value = "/totalBills", method = RequestMethod.GET )
//    public CheckedBillOrderDTO getTotalPriceAllBills() throws ApplicationException {
//        if ( logger.isDebugEnabled() ) {
//            logger.debug( "Getting total price of bill order items..." );
//        }
//        return billItemService.getReportAllBillOrders();
//
//    }
//
//    /**
//     * Update bill order
//     *
//     * @param billOrderItemDTO
//     * @throws ApplicationException
//     */
//    @RequestMapping( method = RequestMethod.PUT )
//    public void editBillOrder( @RequestBody List<BillOrderItemEditDTO> billOrderItemDTOs ) throws ApplicationException {
//        if ( logger.isDebugEnabled() ) {
//            logger.debug( "Editing bill order item..." );
//        }
//        billItemService.editBillOrders( billOrderItemDTOs );
//    }
}
