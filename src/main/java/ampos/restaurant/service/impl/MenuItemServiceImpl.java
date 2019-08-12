package ampos.restaurant.service.impl;

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
     * create or update a menu item.
     *
     * @param id
     *            If id is not null, entity with id "id" will be updated
     * @param menuItemDTO
     *            the entity to update
     * @return the persisted entity
     */
    @Override
    public MenuItemDTO save( Long id, MenuItemDTO menuItemDTO ) {
        log.debug( "Request to update MenuItem : {}", menuItemDTO );

        MenuItem menuItem = menuItemMapper.toEntity( menuItemDTO );
        if ( id != null && menuItemRepository.findById( id ).isPresent() ) {
            menuItem.setId( id );
        } else {
            menuItem.setId( 0 );  // Set 0 here because we use DTO as creation request also. It is better to create requestVM type or use Json ignore here.
        }
        menuItemRepository.save( menuItem );
        return menuItemMapper.toDto( menuItem );
    }

    /**
     * Delete a Menu Item
     *
     * @param id
     *            of the menu item to be deleted
     * @return the persisted entity
     */
    @Override
    public void delete( Long id ) {
        log.debug( "Request to delete Menu Item : {}", id );
        menuItemRepository.deleteById( id );
    }

    /**
     * Get all the menu items.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional( readOnly = true )
    public Page<MenuItemDTO> findAll( Pageable pageable ) {
        log.debug( "Request to get all MenuItems" );
        return menuItemRepository.findAll( pageable ).map( menuItemMapper::toDto );
    }

    /**
     * Get one menu item by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional( readOnly = true )
    public MenuItemDTO findById( Long id ) throws ApplicationException {
        log.debug( "Request to get Menu Item : {}", id );
        MenuItem menuItem = menuItemRepository.findById( id ).orElseThrow( ( ) -> new ApplicationException( Constants.MENU_ITEM_NOT_FOUND ) );
        return menuItemMapper.toDto( menuItem );
    }

    /**
     * Search menu items by keyword
     *
     * @param keyword
     * @param pageable
     * @return
     * @throws ApplicationException
     */
    @Override
    public Page<MenuItemDTO> searchMenuItems( String keyword, Pageable pageable ) throws ApplicationException {
        return menuItemRepository.search( keyword, pageable ).map( menuItemMapper::toDto );
    }
}
