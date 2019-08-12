package ampos.restaurant.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BillResponse {
	private Long id;

	private Set<BillItemsResponse> billItems = new HashSet<>();

	private BigDecimal total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<BillItemsResponse> getBillItems() {
		return billItems;
	}

	public void setBillItems(Set<BillItemsResponse> billItems) {
		this.billItems = billItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal totalBill) {
		this.total = totalBill;
	}

}
