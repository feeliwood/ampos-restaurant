package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItemReport;
import ampos.restaurant.domain.dto.BillItemReportDTO;

import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface BillItemReportMapper extends EntityMapper<BillItemReportDTO, BillItemReport> {

    BillItemReportDTO toDto( BillItemReport billItemReport );
}
