package ampos.restaurant.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalBillItemReportDTO {
    private List<BillItemReportDTO> billItemsReport;
    private BigDecimal grandTotal;
}
