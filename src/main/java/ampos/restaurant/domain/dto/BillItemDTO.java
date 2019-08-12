package ampos.restaurant.domain.dto;

import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;

public class BillItemDTO implements Serializable {

    private Long id;

    private int quantity;

    private MenuItemDTO menuItem;

    private Instant orderedTime;

    private Long billId;

    private BigDecimal subTotal;

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

    public MenuItemDTO getMenuItem() {
	return menuItem;
    }

    public void setMenuItem( MenuItemDTO menuItem ) {
	this.menuItem = menuItem;
    }

    public Instant getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Instant orderedTime) {
        this.orderedTime = orderedTime;
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
