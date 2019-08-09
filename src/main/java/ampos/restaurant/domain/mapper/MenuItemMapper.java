package ampos.restaurant.domain.mapper;

import java.util.Arrays;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;


/**
 * Mapper for the entity MenuItem and its DTO MenuItemDTO.
 */
@Mapper(componentModel = "spring")
public interface MenuItemMapper extends EntityMapper <MenuItemDTO, MenuItem> {

    @Mapping(source = "menuItem", target = "details", qualifiedByName = "toDetailsDto")
    MenuItemDTO toDto(MenuItem menuItem);

    @Mapping(target = "active", ignore = true)
    @Mapping(source = "menuItemDTO", target = "details", qualifiedByName = "toDetails")
    MenuItem toEntity(MenuItemDTO menuItemDTO);

    @Named("toDetailsDto")
    default List<String> detailsToDetailsDto(MenuItem item) {
        return Arrays.asList(item.getDetails().split("\\s*,\\s*"));
    }

    @Named("toDetails")
    default String detailsDtoToDetails(MenuItemDTO item) {
        String details = String.join(",", item.getDetails());
        return details;
    }

    default MenuItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        return menuItem;
    }
}