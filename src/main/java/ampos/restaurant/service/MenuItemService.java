package ampos.restaurant.service;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Menu Item Service
 */
public interface MenuItemService {
    /**
     * Create or update menu item
     *
     * @param menuItemDTO
     * @throws ApplicationException
     */
    MenuItemDTO createOrUpdateMenuItem( Long id, MenuItemDTO menuItemDTO ) throws ApplicationException;

    /**
     * Delete menu item
     * 
     * @param id
     * @throws ApplicationException
     */
    void deleteMenuItem( Long id );

    /**
     * Get menu item by Id
     * 
     * @param id
     * @return
     * @throws ApplicationException
     */
    MenuItemDTO findMenuItemById( Long id ) throws ApplicationException;

    /**
     * Get list menu items
     * 
     * @param pageable
     * @return
     * @throws ApplicationException
     */
    Page<MenuItemDTO> findAllMenuItems( Pageable pageable ) throws ApplicationException;

    /**
     * Search menu items by keyword
     *
     * @param keyword
     * @param pageable
     * @return
     * @throws ApplicationException
     */
    Page<MenuItemDTO> searchMenuItems( String keyword, Pageable pageable ) throws ApplicationException;
}
