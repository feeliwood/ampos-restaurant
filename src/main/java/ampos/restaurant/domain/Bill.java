package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Bill entity
 */
@Entity
@Table( name = Bill.TABLE_NAME )
public class Bill implements Serializable {
    protected static final String TABLE_NAME = "bill";
    protected static final String MAPPED_MANY_TO_ONE_FIELD_NAME = "bill";
    private static final String ID = "id";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private long id;

    @OneToMany( mappedBy = MAPPED_MANY_TO_ONE_FIELD_NAME, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    private Set<BillItem> billItems = new HashSet<>();

    public Bill() {
        super();
    }

    public Bill( long id, Set<BillItem> billItems ) {
        super();
        this.id = id;
        this.billItems = billItems;
    }

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
    public void setId( long id ) {
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
    public void addBillItem( BillItem billItem ) {
        billItems.add( billItem );
        billItem.setBill( this );
    }

    /**
     * remove billItem from collection
     *
     * @param billItem
     */
    public void removeBillItem( BillItem billItem ) {
        billItems.remove( billItem );
        billItem.setBill( null );
    }

    /**
     * get total price of a bill which is total of all bill items
     *
     */
    public BigDecimal getTotal() {
        return billItems.stream().map( BillItem::getSubTotal ).reduce( BigDecimal.ZERO, BigDecimal::add );
    }
}
