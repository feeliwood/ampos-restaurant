package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.repository.MenuItemRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = { BillMapper.class, MenuItemMapper.class, MenuItemRepository.class, UtilConverter.class } )
public interface BillItemMapper extends EntityMapper <BillItemDTO, BillItem> {

    @Mapping( source = "menuItem.name", target = "menuItemName")
    @Mapping( source = "bill.id", target = "billId")
    BillItemDTO toDto(BillItem billItem);

    @Mapping( source = "menuItemName", target = "menuItem", qualifiedByName = "nameToMenuItem")
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

@Component
class UtilConverter {

    MenuItemRepository menuItemRepository;

    public UtilConverter(MenuItemRepository menuItemRepository) {
	this.menuItemRepository = menuItemRepository;
    }

    @Named("nameToMenuItem")
    MenuItem nameToMenuItem(String menuItemName) {
	return menuItemRepository.findByName( menuItemName ).orElse( null );
    }
}