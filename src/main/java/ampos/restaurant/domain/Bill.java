package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bill. <br>
 * TODO in the future, we can expand this table by adding more information <br>
 * as customer name/id ... <br>
 * Now we only store a simple table with ID.
 */
@Entity
@Table( name = Bill.TABLE_NAME )
public class Bill implements Serializable {
    protected static final String TABLE_NAME = "bill";
    private static final String ID = "id";
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private long id;

    @OneToMany(
                    mappedBy = "bill", // TODO
                    cascade = CascadeType.ALL,
                    orphanRemoval = true,
                    fetch = FetchType.LAZY
    )
    private Set<BillItem> billItems = new HashSet<>();


    /**
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * set id
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return billItems
     */
    public Set<BillItem> getBillItems() {
        return billItems;
    }

    /**
     * set list billItems
     *
     * @param billItems
     */
    public void setBillItems( Set<BillItem> billItems ) {
        this.billItems = billItems;
    }

    /**
     * add billItem to collection
     *
     * @param billItem
     */
    public void addBillItem(BillItem billItem) {
        billItems.add( billItem );
        billItem.setBill(this);
    }

    /**
     * remove billItem from collection
     *
     * @param billItem
     */
    public void removeBillItem(BillItem billItem) {
        billItems.remove( billItem );
        billItem.setBill(null);
    }

    /**
     * get total price of a bill which is total of all bill items
     *
     */
    public BigDecimal getTotal() {
        return billItems.stream()
                        .map(BillItem::getSubTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
