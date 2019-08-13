package ampos.restaurant.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillItemsRequest {
    private Long id;
    private int quantity;
    private MenuRequest menuItem;
    private Long billId;
    private BigDecimal subTotal;

    public BillItemsRequest( Long id, int quantity, MenuRequest menuItem, String orderedTime, Long billId, BigDecimal subTotal ) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.menuItem = menuItem;
        this.billId = billId;
        this.subTotal = subTotal;
    }
}
