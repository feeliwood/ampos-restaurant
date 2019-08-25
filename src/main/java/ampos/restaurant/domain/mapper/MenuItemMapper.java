package ampos.restaurant.domain.mapper;

import java.util.Arrays;
import java.util.List;

import ampos.restaurant.domain.dto.MenuItemRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;

/**
 * Mapper for the entity MenuItem and its DTO MenuItemDTO.
 */
@Mapper( componentModel = "spring" )
public interface MenuItemMapper extends GenericMapper<MenuItemDTO, MenuItem, MenuItemRequestDTO> {

    /**
     *
     * @param menuItem
     * @return menuItemDTO which is DTO of menuItem
     */
    @Mapping( source = "menuItem.details", target = "details", qualifiedByName = "toDtoDetails" )
    MenuItemDTO entityToDto( MenuItem menuItem );

    /**
     *
     * @param menuItemRequestDTO
     * @return menuItem which is entity of menuItemDTO
     */
    @Mapping( source = "menuItemRequestDTO.details", target = "details", qualifiedByName = "toEntityDetails" )
    MenuItem requestToEntity( MenuItemRequestDTO menuItemRequestDTO );

    /**
     * Helper method to map "details" field from entity (which is of type String) to "details" field of dto (which is of type List<String>)
     * @param details comma separated string of details
     * @return return list of details
     */
    @Named( "toDtoDetails" )
    default List<String> toDtoDetails( String details ) {
        return Arrays.asList( details.split( "\\s*,\\s*" ) );
    }

    /**
     * Helper method to map "details" field from dto (which is of type List<String>) to "details" field of dto (which is of type String)
     * @param listDetails list of details
     * @return return a comma-separated string
     */
    @Named( "toEntityDetails" )
    default String toEntityDetails( List<String> listDetails ) {
        String details = String.join( ",", listDetails );
        return details;
    }

    /**
     * Create a dummy MenuItem object from id to prevent infinite loop in bidirectional relationship when mapping
     * @param id
     * @return
     */
    @Named( "fromIdToMenuItem" )
    default MenuItem fromId( Long id ) {
        if ( id == null ) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId( id );
        return menuItem;
    }
}