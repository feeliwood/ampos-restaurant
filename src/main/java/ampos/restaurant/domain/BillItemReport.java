package ampos.restaurant.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillItemReport {
    private MenuItem menuItem;
    private long quantity;
    private BigDecimal subTotal;
}
