package ampos.restaurant.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TotalReportDTO {
    private TotalBillItemReportDTO billItemReport;
    private TotalBillReportDTO billReport;

    public TotalReportDTO(TotalBillItemReportDTO billItemReport, TotalBillReportDTO billReport) {
        this.billItemReport = billItemReport;
        this.billReport = billReport;
    }
}
