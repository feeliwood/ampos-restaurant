package ampos.restaurant.domain.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillItemReportDTO {
    private String name;
    private long quantity;
    private BigDecimal totalPrice;
}
