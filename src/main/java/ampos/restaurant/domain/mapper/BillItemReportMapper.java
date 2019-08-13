package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItemReport;
import ampos.restaurant.domain.dto.BillItemReportDTO;

import org.mapstruct.Mapper;

/**
 * Bill item report mapper
 */
@Mapper( componentModel = "spring", uses = { MenuItemMapper.class })
public interface BillItemReportMapper extends EntityMapper<BillItemReportDTO, BillItemReport> {

    /**
     *
     * @param billItemReport
     * @return billItemReportDTO which is DTO of billItemReport
     */
    BillItemReportDTO toDto( BillItemReport billItemReport );
}
