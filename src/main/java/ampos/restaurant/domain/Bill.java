package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Bill entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = Bill.TABLE_NAME )
public class Bill implements Serializable, DomainEntity<Long> {
    protected static final String TABLE_NAME = "bill";
    protected static final String MAPPED_MANY_TO_ONE_FIELD_NAME = "bill";
    private static final String ID = "id";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private Long id;

    @OneToMany( mappedBy = MAPPED_MANY_TO_ONE_FIELD_NAME, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    private Set<BillItem> billItems = new HashSet<>();

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
