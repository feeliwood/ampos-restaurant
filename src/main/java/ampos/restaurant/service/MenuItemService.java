package ampos.restaurant.service;

import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.domain.dto.MenuItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Menu Item Service
 */
public interface MenuItemService {
    /**
     *  Update menu item
     *
     * @param menuItemDTO
     * @throws ApplicationException
     */
    MenuItemDTO save( Long id,  MenuItemDTO menuItemDTO ) throws ApplicationException;

    /**
     * Delete menu item
     * 
     * @param id
     * @throws ApplicationException
     */
    void delete( Long id );

    /**
     * Get menu item by Id
     * 
     * @param id
     * @return
     * @throws ApplicationException
     */
    MenuItemDTO findById( Long id ) throws ApplicationException;

    /**
     * Get list menu items
     * 
     * @param pageable
     * @return
     * @throws ApplicationException
     */
    Page<MenuItemDTO> findAll( Pageable pageable ) throws ApplicationException;

//    /**
//     * Search menu items by keyword
//     *
//     * @param keyword
//     * @return
//     * @throws ApplicationException
//     */
//    List<MenuItemDTO> searchMenuItems( String keyword ) throws ApplicationException;
//

}
