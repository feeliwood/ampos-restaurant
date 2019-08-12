package ampos.restaurant.models;

import java.math.BigDecimal;

import ampos.restaurant.domain.dto.MenuItemDTO;

public class BillItemsResponse {
	
	 private Long id;

	    private int quantity;

	    private MenuItemDTO menuItem;

	    private String orderedTime;

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

	   

	    public String getOrderedTime() {
			return orderedTime;
		}

		public void setOrderedTime(String orderedTime) {
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
