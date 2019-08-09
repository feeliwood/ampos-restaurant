package ampos.restaurant.domain.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * MenuItem data transfer object
 */
public class BillDTO implements Serializable {

    private long id;

    private Set<BillItemDTO> billItems = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public Set<BillItemDTO> getBillItems() {
        return billItems;
    }

    public void setBillItems( Set<BillItemDTO> billItems ) {
        this.billItems = billItems;
    }
}
