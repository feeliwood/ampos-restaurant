package ampos.restaurant.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public class TotalBillReportDTO {

    private List<BillItemReportDTO> billItemsReport;

    BigDecimal grandTotal;

    public List<BillItemReportDTO> getBillItemsReport() {
	return billItemsReport;
    }

    public void setBillItemsReport( List<BillItemReportDTO> billItemsReport ) {
	this.billItemsReport = billItemsReport;
    }

    public BigDecimal getGrandTotal() {
	return grandTotal;
    }

    public void setGrandTotal( BigDecimal grandTotal ) {
	this.grandTotal = grandTotal;
    }
}
