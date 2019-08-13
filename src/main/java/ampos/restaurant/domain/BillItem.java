package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * BillItem entity
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = BillItem.TABLE_NAME )
public class BillItem implements Serializable {
    protected static final String TABLE_NAME = "bill_order_item";
    private static final String ID = "id";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String ORDERED_TIME_COLUMN = "ordered_time";
    private static final String FK_MENU_ITEM_ID_COLUMN = "fk_menu_item_id";
    private static final String FK_BILL_ID_COLUMN = "fk_bill_id";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private long id;

    @Column( name = QUANTITY_COLUMN )
    private int quantity;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = FK_MENU_ITEM_ID_COLUMN )
    private MenuItem menuItem;

    @Column( name = ORDERED_TIME_COLUMN, nullable = false )
    private Instant orderedTime;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = FK_BILL_ID_COLUMN )
    private Bill bill;

    public BillItem( long id, int quantity, MenuItem menuItem, Instant orderedTime ) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.menuItem = menuItem;
        this.orderedTime = orderedTime;
    }

    /**
     *
     * @return sub total of a bill item which is equal menu item price multiply
     *         quantity
     */
    public BigDecimal getSubTotal() {
        return BigDecimal.valueOf( quantity ).multiply( menuItem.getPrice() );
    }
}
