package ampos.restaurant.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillItemDTO implements Serializable {

    private Long id;
    private int quantity;
    private MenuItemDTO menuItem;
    private Instant orderedTime;
    private Long billId;
    private BigDecimal subTotal;
}