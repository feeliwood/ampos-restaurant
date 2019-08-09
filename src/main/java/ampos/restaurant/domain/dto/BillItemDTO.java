package ampos.restaurant.domain.dto;

import java.io.Serializable;

public class BillItemDTO implements Serializable {

    private long id;

    private int quantity;

    private String menuItemName;

    private long orderedTime;

    private long billId;

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName( String menuItemName ) {
        this.menuItemName = menuItemName;
    }

    public long getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime( long orderedTime ) {
        this.orderedTime = orderedTime;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId( long billId ) {
        this.billId = billId;
    }
}
