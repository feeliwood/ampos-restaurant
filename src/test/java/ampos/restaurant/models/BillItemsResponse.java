package ampos.restaurant.models;

import java.math.BigDecimal;

import ampos.restaurant.domain.dto.MenuItemDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillItemsResponse {
    private Long id;
    private int quantity;
    private MenuItemDTO menuItem;
    private String orderedTime;
    private Long billId;
    private BigDecimal subTotal;
}
