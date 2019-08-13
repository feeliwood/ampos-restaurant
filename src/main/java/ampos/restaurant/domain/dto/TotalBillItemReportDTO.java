package ampos.restaurant.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Total bill item report DTO
 */
public class TotalBillItemReportDTO {
    private List<BillItemReportDTO> billItems;
}
