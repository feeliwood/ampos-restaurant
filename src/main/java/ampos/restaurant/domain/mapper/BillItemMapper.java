package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.dto.BillItemDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { BillMapper.class, MenuItemMapper.class } )
public interface BillItemMapper extends EntityMapper <BillItemDTO, BillItem> {

    BillItemDTO toDto(BillItem billItem);

    BillItem toEntity(BillItemDTO billItemDTO);

    default BillItem fromId(Long id) {
	if (id == null) {
	    return null;
	}
	BillItem Bill = new BillItem();
	Bill.setId(id);
	return Bill;
    }
}
