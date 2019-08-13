package ampos.restaurant.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillItemReport {
    private String name;
    private long quantity;
    private BigDecimal totalPrice;

    public BillItemReport( String name, long quantity, BigDecimal totalPrice ) {
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
