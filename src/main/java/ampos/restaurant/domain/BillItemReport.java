package ampos.restaurant.domain;

import java.math.BigDecimal;

public class BillItemReport {
    private String name;

    private long quantity;

    private BigDecimal totalPrice;

    public BillItemReport() {}

    public BillItemReport( String name, long quantity, BigDecimal totalPrice ) {
	this.name = name;
	this.quantity = quantity;
	this.totalPrice = totalPrice;
    }

    public String getName() {
	return name;
    }

    public void setName( String name ) {
	this.name = name;
    }

    public long getQuantity() {
	return quantity;
    }

    public void setQuantity( long quantity ) {
	this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice( BigDecimal totalPrice ) {
	this.totalPrice = totalPrice;
    }
}
