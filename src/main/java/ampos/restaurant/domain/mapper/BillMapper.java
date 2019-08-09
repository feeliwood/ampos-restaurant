package ampos.restaurant.domain.mapper;

import org.mapstruct.Mapper;
import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.dto.BillDTO;

/**
 * Mapper for the entity Bill and its DTO BillDTO.
 */
@Mapper(componentModel = "spring", uses = { BillItemMapper.class } )
public interface BillMapper extends EntityMapper <BillDTO, Bill> {

    BillDTO toDto(Bill bill);

    Bill toEntity(BillDTO billDTO);

    default Bill fromId(Long id) {
	if (id == null) {
	    return null;
	}
	Bill Bill = new Bill();
	Bill.setId(id);
	return Bill;
    }
}
