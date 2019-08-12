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
    MenuItemDTO save( Long id, MenuItemDTO menuItemDTO ) throws ApplicationException;

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
