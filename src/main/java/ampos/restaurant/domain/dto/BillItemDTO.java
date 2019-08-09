package ampos.restaurant.domain.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class BillItemDTO implements Serializable {

    private long id;

    private int quantity;

    private MenuItemDTO menuItem;

    private ZonedDateTime orderedTime;

    private BillDTO bill;

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

    public MenuItemDTO getMenuItem() {
	return menuItem;
    }

    public void setMenuItem( MenuItemDTO menuItem ) {
	this.menuItem = menuItem;
    }

    public ZonedDateTime getOrderedTime() {
	return orderedTime;
    }

    public void setOrderedTime( ZonedDateTime orderedTime ) {
	this.orderedTime = orderedTime;
    }

    public BillDTO getBill() {
	return bill;
    }

    public void setBill( BillDTO bill ) {
	this.bill = bill;
    }
}
