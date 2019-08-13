package ampos.restaurant.models;

import java.math.BigDecimal;

public class BillItemsRequest {
    private Long id;

    private int quantity;

    private MenuRequest menuItem;

    private Long billId;

    private BigDecimal subTotal;

    public BillItemsRequest() {
        super();
    }

    public BillItemsRequest( Long id, int quantity, MenuRequest menuItem, String orderedTime, Long billId, BigDecimal subTotal ) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.menuItem = menuItem;
        this.billId = billId;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    public MenuRequest getMenuItem() {
        return menuItem;
    }

    public void setMenuItem( MenuRequest menuItem ) {
        this.menuItem = menuItem;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId( Long billId ) {
        this.billId = billId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal( BigDecimal subTotal ) {
        this.subTotal = subTotal;
    }

}
