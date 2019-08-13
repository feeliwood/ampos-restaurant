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

}
