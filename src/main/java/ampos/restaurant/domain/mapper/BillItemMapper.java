package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.repository.MenuItemRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { BillMapper.class, MenuItemMapper.class, MenuItemRepository.class } )
public interface BillItemMapper extends EntityMapper <BillItemDTO, BillItem> {

    @Mapping( source = "bill.id", target = "billId")
    @Mapping( expression = "java(billItem.getSubTotal())", target = "subTotal" )
    BillItemDTO toDto(BillItem billItem);

    @Mapping( source = "billId", target = "bill")
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