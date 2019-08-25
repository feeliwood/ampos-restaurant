package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItemReport;
import ampos.restaurant.domain.dto.BillItemReportDTO;

import ampos.restaurant.domain.dto.BillItemRequestDTO;
import org.mapstruct.Mapper;

/**
 * Bill item report mapper
 */
@Mapper( componentModel = "spring", uses = { MenuItemMapper.class })
public interface BillItemReportMapper extends GenericMapper<BillItemReportDTO, BillItemReport, BillItemRequestDTO> {

    /**
     *
     * @param billItemReport
     * @return billItemReportDTO which is DTO of billItemReport
     */
    BillItemReportDTO entityToDto( BillItemReport billItemReport );
}
