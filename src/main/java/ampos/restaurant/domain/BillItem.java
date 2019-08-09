package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: BillItem.
 */
@Entity
@Table( name = BillItem.TABLE_NAME )
public class BillItem implements Serializable {
    protected static final String TABLE_NAME = "bill_order_item";
    private static final String ID = "id";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String ORDERED_TIME_COLUMN = "ordered_time";
    private static final String FK_MENU_ITEM_ID_COLUMN = "fk_menu_item_id";
    private static final String FK_BILL_ID_COLUMN = "fk_bill_id";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private long id;

    @Column(name = QUANTITY_COLUMN)
    private int quantity;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = FK_MENU_ITEM_ID_COLUMN )
    private MenuItem menuItem;

    @Column( name = ORDERED_TIME_COLUMN, nullable = false )
    private Instant orderedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FK_BILL_ID_COLUMN )
    private Bill bill;

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
     * @return menuItem
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * set MenuItem
     * 
     * @param menuItem
     */
    public void setMenuItem( MenuItem menuItem ) {
        this.menuItem = menuItem;
    }

    /**
     *
     * @return billOrder
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * set bill
     *
     * @param bill
     */
    public void setBill( Bill bill ) {
        this.bill = bill;
    }

    /**
     * 
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set quantity
     * 
     * @param quantity
     */
    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    /**
     *
     * @return ordered time
     */
    public Instant getOrderedTime() {
        return orderedTime;
    }

    /**
     * set orderedTime
     *
     * @param orderedTime
     */
    public void setOrderedTime( Instant orderedTime ) {
        this.orderedTime = orderedTime;
    }

    /**
     *
     * @return sub total of a bill item which is equal menu item price multiply quantity
     */
    public BigDecimal getSubTotal() {
        return BigDecimal.valueOf( quantity ).multiply( menuItem.getPrice());
    }

}
