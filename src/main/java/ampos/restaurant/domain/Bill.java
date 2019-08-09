package ampos.restaurant.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
                    orphanRemoval = true
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
    public void setId(Long id) {
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
}
