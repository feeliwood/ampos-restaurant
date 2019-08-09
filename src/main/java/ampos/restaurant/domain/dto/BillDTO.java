package ampos.restaurant.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * MenuItem data transfer object
 */
public class BillDTO implements Serializable {

    private Long id;

    private Set<BillItemDTO> billItems = new HashSet<>();

    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Set<BillItemDTO> getBillItems() {
        return billItems;
    }

    public void setBillItems( Set<BillItemDTO> billItems ) {
        this.billItems = billItems;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal( BigDecimal totalBill ) {
        this.total = totalBill;
    }
}
