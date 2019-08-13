package ampos.restaurant.domain.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Bill item report DTO
 */
public class BillItemReportDTO {
    private MenuItemDTO menuItem;
    private long quantity;
    private BigDecimal subTotal;
}
