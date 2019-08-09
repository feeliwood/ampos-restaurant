package ampos.restaurant.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;

/**
 * Mapper for the entity MenuItem and its DTO MenuItemDTO.
 */
@Mapper(componentModel = "spring")
public interface MenuItemMapper extends EntityMapper <MenuItemDTO, MenuItem> {

    //TO DO
    MenuItemDTO toDto(MenuItem menuItem);

    // TO DO 
    @Mapping(target = "active", ignore = true)
    MenuItem toEntity(MenuItemDTO menuItemDTO);

    default MenuItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        return menuItem;
    }
}
