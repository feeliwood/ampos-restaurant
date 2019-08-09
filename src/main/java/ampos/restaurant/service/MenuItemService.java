package ampos.restaurant.service;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.web.rest.vm.MenuItemRequestVM;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Menu Item Service
 */
public interface MenuItemService {
    /**
     * Create menu item
     * 
     * @param menuItemRequestVM
     * @param file
     * @throws ApplicationException
     */
    MenuItemDTO create( MenuItemRequestVM menuItemRequestVM, MultipartFile file ) throws ApplicationException;

    /**
     * Update menu item
     *
     * @param menuItemRequestVM
     * @param file
     * @throws ApplicationException
     */
    MenuItemDTO update( Long id, MenuItemRequestVM menuItemRequestVM, MultipartFile file ) throws ApplicationException;

    /**
     * Delete menu item
     * 
     * @param id
     * @throws ApplicationException
     */
    void delete( Long id ) throws ApplicationException;

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
     * Search menu items
     * 
     * @param keyword
     * @param pageable
     * @return
     */
    Page<MenuItemDTO> search( String keyword, Pageable pageable );

}
