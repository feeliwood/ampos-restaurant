package ampos.restaurant.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
/**
 * Total bill report DTO
 */
public class TotalBillReportDTO {
    private int numberOfBills;
    private BigDecimal grandTotal;
    private List<BillDTO> bills;
}
