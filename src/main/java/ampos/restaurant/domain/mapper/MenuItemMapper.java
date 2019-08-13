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
@Mapper( componentModel = "spring" )
public interface MenuItemMapper extends EntityMapper<MenuItemDTO, MenuItem> {

    /**
     *
     * @param menuItem
     * @return menuItemDTO which is DTO of menuItem
     */
    @Mapping( source = "menuItem", target = "details", qualifiedByName = "toDetailsDto" )
    MenuItemDTO toDto( MenuItem menuItem );

    /**
     *
     * @param menuItemDTO
     * @return menuItem which is entity of menuItemDTO
     */
    @Mapping( source = "menuItemDTO", target = "details", qualifiedByName = "toDetails" )
    MenuItem toEntity( MenuItemDTO menuItemDTO );

    /**
     * Helper method to map "details" field from entity (which is of type String) to "details" field of dto (which is of type List<String>)
     * @param item entity menu item
     * @return return list of details
     */
    @Named( "toDetailsDto" )
    default List<String> detailsToDetailsDto( MenuItem item ) {
        return Arrays.asList( item.getDetails().split( "\\s*,\\s*" ) );
    }

    /**
     * Helper method to map "details" field from dto (which is of type List<String>) to "details" field of dto (which is of type String)
     * @param item DTO menu item
     * @return return a comma-separated string
     */
    @Named( "toDetails" )
    default String detailsDtoToDetails( MenuItemDTO item ) {
        String details = String.join( ",", item.getDetails() );
        return details;
    }

    /**
     * Create a dummy MenuItem object from id to prevent infinite loop in bidirectional relationship when mapping
     * @param id
     * @return
     */
    default MenuItem fromId( Long id ) {
        if ( id == null ) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId( id );
        return menuItem;
    }
}