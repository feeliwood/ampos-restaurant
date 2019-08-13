package ampos.restaurant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;

/**
 * Menu Item Service
 */
public interface MenuItemService {
    /**
     * Create or update menu item
     *
     * @param id
     *            : of the menu item to be updated ( in case of updating menu
     *            item )
     * @param menuItemDTO
     *            : the menu item to be persisted
     * @return
     * @throws ApplicationException
     */
    MenuItemDTO createOrUpdateMenuItem( Long id, MenuItemDTO menuItemDTO ) throws ApplicationException;

    /**
     * Delete menu item
     *
     * @param id
     *            : of the menu item to be deleted
     */
    void deleteMenuItem( Long id );

    /**
     * Get menu item by Id
     *
     * @param id
     *            : of the menu item to be retrieved
     * @return
     * @throws ApplicationException
     */
    MenuItemDTO findMenuItemById( Long id ) throws ApplicationException;

    /**
     * Get list menu items
     *
     * @param pageable
     *            : the pagination information
     * @return
     * @throws ApplicationException
     */
    Page<MenuItemDTO> findAllMenuItems( Pageable pageable ) throws ApplicationException;

    /**
     * Search menu items by keyword
     *
     * @param keyword
     *            : the keyword of menu title or description or additional
     *            details.
     * @param pageable
     *            : the pagination information
     * @return
     * @throws ApplicationException
     */
    Page<MenuItemDTO> searchMenuItems( String keyword, Pageable pageable ) throws ApplicationException;
}
