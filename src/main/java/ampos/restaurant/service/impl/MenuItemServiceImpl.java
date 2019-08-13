package ampos.restaurant.service.impl;

import java.util.Arrays;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.domain.mapper.MenuItemMapper;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.repository.MenuItemRepository;
import ampos.restaurant.service.MenuItemService;
import ampos.restaurant.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Menu item service implementation
 *
 */
@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

    private final Logger log = LoggerFactory.getLogger( MenuItemServiceImpl.class );

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    public MenuItemServiceImpl( MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper ) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

	/**
	 * Create or update menu item
	 * 
	 * @param id
	 *            : of the menu item to be updated ( in case of updating menu item )
	 * @param menuItemDTO
	 *            : the menu item to be persisted
	 * @return
	 * @throws ApplicationException
	 */
    @Override
    public MenuItemDTO createOrUpdateMenuItem( Long id, MenuItemDTO menuItemDTO ) {
        log.debug( "Request to update MenuItem : {}", menuItemDTO );

        MenuItem menuItem = menuItemMapper.toEntity( menuItemDTO );
        if ( id != null && menuItemRepository.findById( id ).isPresent() ) {
            menuItem.setId( id );
        } else {
            menuItem.setId( 0 ); // Set 0 here because we use DTO as creation request also. It is better to create requestVM type or use Json ignore here.
        }
        menuItemRepository.save( menuItem );
        return menuItemMapper.toDto( menuItem );
    }

    /**
     * Delete menu item
     * 
     * @param id
	 *            : of the menu item to be deleted
     */
    @Override
    public void deleteMenuItem( Long id ) {
        log.debug( "Request to delete Menu Item : {}", id );
        menuItemRepository.deleteAllMenuItemWithIds( Arrays.asList(id) );
    }

    /**
     * Get list menu items
     * 
     * @param pageable
     *            : the pagination information
     * @return
     * @throws ApplicationException
     */
    @Override
    @Transactional( readOnly = true )
    public Page<MenuItemDTO> findAllMenuItems( Pageable pageable ) {
        log.debug( "Request to get all MenuItems" );
        return menuItemRepository.findAll( pageable ).map( menuItemMapper::toDto );
    }

    /**
     * Get menu item by Id
     * 
     * @param id
	 *            : of the menu item to be retrieved
     * @return
     * @throws ApplicationException
     */
    @Override
    @Transactional( readOnly = true )
    public MenuItemDTO findMenuItemById( Long id ) throws ApplicationException {
        log.debug( "Request to get Menu Item : {}", id );
        MenuItem menuItem = menuItemRepository.findById( id ).orElseThrow( () -> new ApplicationException( Constants.MENU_ITEM_NOT_FOUND ) );
        return menuItemMapper.toDto( menuItem );
    }

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
    @Override
    @Transactional( readOnly = true )
    public Page<MenuItemDTO> searchMenuItems( String keyword, Pageable pageable ) throws ApplicationException {
        return menuItemRepository.search( keyword.toLowerCase(), pageable ).map( menuItemMapper::toDto );
    }
}
