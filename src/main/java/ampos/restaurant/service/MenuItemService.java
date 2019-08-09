package ampos.restaurant.service;

import java.util.List;
import java.util.Optional;

import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.domain.dto.MenuItemDTO;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing MenuItem.
 */
public interface MenuItemService {
    /**
     * Create menu item
     * 
     * @param menuItemDTO
     * @param file
     * @throws ApplicationException
     */
    MenuItemDTO save( MenuItemDTO menuItemDTO, MultipartFile file ) throws ApplicationException;

    /**
     *  Update menu item
     *
     * @param menuItemDTO
     * @param file
     * @throws ApplicationException
     */
    MenuItemDTO update( Long id, MenuItemDTO menuItemDTO, MultipartFile file ) throws ApplicationException;

    /**
     * Delete menu item
     * 
     * @param id
     * @throws ApplicationException
     */
    void delete( Long id ) throws ApplicationException;

//    /**
//     * Edit menu item
//     *
//     * @param menuItemDTO
//     * @param file
//     * @throws ApplicationException
//     */
//    void editMenuItem( MenuItemDTO menuItemDTO, MultipartFile file ) throws ApplicationException;

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
    Page<MenuItemDTO> findAll( Pageable pageable ) throws ApplicationException;;

//    /**
//     * Search menu items by keyword
//     *
//     * @param keyword
//     * @return
//     * @throws ApplicationException
//     */
//    List<MenuItemDTO> searchMenuItems( String keyword ) throws ApplicationException;
//
//    /**
//     * Load image item
//     *
//     * @param fileName
//     * @return
//     * @throws ApplicationException
//     */
//    Resource loadImage( String fileName ) throws ApplicationException;
}
