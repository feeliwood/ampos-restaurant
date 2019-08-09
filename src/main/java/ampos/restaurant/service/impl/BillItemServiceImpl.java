package ampos.restaurant.service.impl;

import ampos.restaurant.service.BillItemService;

/**
 * Bill order mgmt service impl which is used by controller to retrieve data
 * layer
 *
 */
//@Service
public class BillItemServiceImpl implements BillItemService {
//    @Autowired
//    private BillOrderItemRepository billOrderItemRepository;
//    @Autowired
//    private BillOrderRepository billOrderRepository;
//    @Autowired
//    private MenuRepository menuRepository;
//    @Autowired
//    private DefaultDTOMapper mapper;
//
//    @Override
//    public void createBillOrder( BillOrderItemInputDTO billOrderMenu ) throws ApplicationException {
//        Bill bill = new Bill();
//        billOrderRepository.save( bill );
//        for ( Menu menu : billOrderMenu.getMenus() ) {
//            BillItem billItem = new BillItem();
//            //Find menuItem by name
//            MenuItem menuItem = menuRepository.findByTitle( menu.getTitle() );
//            if ( menuItem == null )
//                throw new ApplicationException( RestaurantConstants.MENU_ITEM_NOT_FOUND );
//            billItem.setMenuItem( menuItem );
//            billItem.setBillOrder( bill );
//            /*bill.getBillItems().add( billItem );*/
//            billItem.setQuantity( menu.getQuantity() );
//            billItem.setOrderedTime( new Date() );
//            billOrderItemRepository.save( billItem );
//
//        }
//    }
//
//    @Override
//    public List<BillOrderItemOuputDTO> getBillOrderByBillId( Long id ) throws ApplicationException {
//        List<BillOrderItemOuputDTO> billOrderItemDTOs = new ArrayList<>();
//        List<BillItem> bills = billOrderItemRepository.findBillOrderByBillId( id );
//        for ( BillItem bill : bills ) {
//            BillOrderItemOuputDTO billOrderItemDTO = mapper.convert( bill, BillOrderItemOuputDTO.class, RestaurantConstants.ORDER_ITEM_SHOW );
//            billOrderItemDTO.setPrices( bill.getQuantity() * bill.getMenuItem().getPrice() );
//            billOrderItemDTOs.add( billOrderItemDTO );
//
//        }
//        return billOrderItemDTOs;
//    }
//
//    @Override
//    public void editBillOrder( BillOrderItemEditDTO billOrderItemDTO ) throws ApplicationException {
//        if ( billOrderItemDTO == null )
//            return;
//        BillItem billItem = billOrderItemRepository.findOne( billOrderItemDTO.getId() );
//        if ( billItem == null )
//            throw new ApplicationException( RestaurantConstants.BILL_ORDER_ITEM_NOT_FOUND );
//
//        //Delete if menu menuItem name is empty/null;
//        if ( billOrderItemDTO.getTitle() == null || billOrderItemDTO.getTitle().isEmpty() ) {
//            deleteBillOrder( billItem );
//            return;
//        }
//
//        //for editing
//        MenuItem menuItem = menuRepository.findByTitle( billOrderItemDTO.getTitle() );
//        if ( menuItem == null )
//            throw new ApplicationException( RestaurantConstants.MENU_ITEM_NOT_FOUND );
//        billItem.setBillOrder( billItem.getBillOrder() );
//        billItem.setMenuItem( menuItem );
//        billItem.setQuantity( billOrderItemDTO.getQuantity() );
//        billOrderItemRepository.save( billItem );
//
//    }
//
//    @Override
//    public void editBillOrders( List<BillOrderItemEditDTO> billOrderItemDTOs ) throws ApplicationException {
//        for ( BillOrderItemEditDTO billOrderItem : billOrderItemDTOs ) {
//            editBillOrder( billOrderItem );
//        }
//    }
//
//    @Override
//    public CheckedBillOrderDTO checkBillOrder( Long id ) throws ApplicationException {
//        return caculateTotalPrices( billOrderItemRepository.checkBill( id ) );
//    }
//
//    @Override
//    public CheckedBillOrderDTO getReportAllBillOrders() {
//        //return caculateTotalPrices( billOrderItemRepository.sumPricesOfBillOrder() );
//        return caculateTotalPrices( billOrderItemRepository.sumPricesOfBillOrder() );
//
//    }
//
//    /**
//     * Caculate total prices of bill order
//     *
//     * @param bills
//     * @return
//     */
//    private CheckedBillOrderDTO caculateTotalPrices( List<BillOrderItemDTO> bills ) {
//        CheckedBillOrderDTO checkedOrderDTO = new CheckedBillOrderDTO();
//        checkedOrderDTO.setOrderItems( bills );
//        bills.stream().forEach( bill -> {
//            checkedOrderDTO.setTotalPirces( checkedOrderDTO.getTotalPirces() + bill.getPrice() * bill.getQuantity() );
//        } );
//        return checkedOrderDTO;
//    }
//
//    @Override
//    public List<BillOrderItemOuputDTO> getAllBillOrderItems() throws ApplicationException {
//        return (List<BillOrderItemOuputDTO>) mapper.convertCollection( billOrderItemRepository.findAll(), BillOrderItemOuputDTO.class );
//    }
//
//    @Override
//    public void deleteBillOrder( BillItem billItem ) throws ApplicationException {
//        billItem.getMenuItem().getBillOrderItems().remove( billItem );
//        billItem.getBillOrder().getBillItems().remove( billItem );
//        billOrderItemRepository.delete( billItem.getId() );
//    }
}
