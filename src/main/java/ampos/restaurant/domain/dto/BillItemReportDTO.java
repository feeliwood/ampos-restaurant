package ampos.restaurant.domain.dto;

import java.math.BigDecimal;

public class BillItemReportDTO {

    private String name;

    private long quantity;

    private BigDecimal totalPrice;

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
