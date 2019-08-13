package ampos.restaurant.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.MenuItemService;
import ampos.restaurant.web.rest.util.HeaderUtil;
import ampos.restaurant.web.rest.util.PaginationUtil;
import ampos.restaurant.web.rest.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Menu Item
 * 
 */
@RestController
@RequestMapping( MenuItemResource.MENU_ITEM__MAPPING )
public class MenuItemResource {

    public static final String MENU_ITEM__MAPPING = "/menu-items";
    private static final String ENTITY_NAME = "menu-item";
    private static final Logger logger = LoggerFactory.getLogger( MenuItemResource.class );

    private MenuItemService menuItemService;

    public MenuItemResource( MenuItemService menuItemService ) {
        this.menuItemService = menuItemService;
    }

    /**
     * Create menu item
     *
     * @Param menuItemDTO: contain data about the menu item to be created
     * @throws ApplicationException
     */
    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem( @RequestBody MenuItemDTO menuItemDTO ) throws ApplicationException, URISyntaxException {
        logger.debug( "Creating menu item...." );

        MenuItemDTO result = menuItemService.save( null, menuItemDTO );
        return ResponseEntity.created( new URI( "/menu/items/" + result.getId() ) )
        					 .headers( HeaderUtil.createEntityCreationAlert( ENTITY_NAME, result.getId().toString() ) )
        					 .body( result );
    }

    /**
     * PUT /items : Updates an existing menu item.
     *
     * @param menuItemDTO
     *            the clientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         clientDTO,
     */
    @PutMapping( "/{id}" )
    public ResponseEntity<MenuItemDTO> updateMenuItem( @PathVariable Long id, @RequestBody MenuItemDTO menuItemDTO ) throws ApplicationException {
        logger.debug( "REST request to update Menu Item : {}", menuItemDTO );

        MenuItemDTO result = menuItemService.save( id, menuItemDTO );
        return ResponseEntity.ok()
        					 .headers( HeaderUtil.createEntityUpdateAlert( ENTITY_NAME, result.getId().toString() ) )
        					 .body( result );
    }

    /**
     * GET /items/:id : get the "id" menu item.
     *
     * @param id
     *            the id of the menuItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         menuItemDTO, or with status 404 (Not Found)
     */
    @GetMapping( "/{id}" )
    public ResponseEntity<MenuItemDTO> getMenuItem( @PathVariable Long id ) throws ApplicationException {
        logger.debug( "REST request to get Menu Item : {}", id );
        MenuItemDTO menuItemDTO = menuItemService.findById( id );
        return ResponseUtil.wrapOrNotFound( Optional.ofNullable( menuItemDTO ) );
    }

    /**
     * GET /items : get all the menu items.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menu
     *         items in body
     */
    @GetMapping
    public ResponseEntity<Page<MenuItemDTO>> getAllMenuItems( Pageable pageable ) throws ApplicationException {
        logger.debug( "REST request to get a page of Menu Items" );
        Page<MenuItemDTO> page = menuItemService.findAll( pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( page, "/menu/items" );
        return new ResponseEntity<>( page, headers, HttpStatus.OK );
    }

    /**
     * DELETE /items/:id : delete the "id" menu item.
     *
     * @param id
     *            the id of the menuItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteMenuItem( @PathVariable Long id ) {
        logger.debug( "REST request to delete menu item : {}", id );
        menuItemService.delete( id );
        return ResponseEntity.ok()
        					 .headers( HeaderUtil.createEntityDeletionAlert( ENTITY_NAME, id.toString() ) )
        					 .build();
    }
    
    /**
     * GET /items/search?keyword=<keyword> : search menu items by keyword.
     *
     * @param keyword
     *            the keyword of menu title or description or additional
     *            details.
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menu
     *         items in body
     */
    @GetMapping( "/search" )
    public ResponseEntity<Page<MenuItemDTO>> searchMenuItems( @RequestParam( value = "keyword" ) String keyword, Pageable pageable ) throws ApplicationException {
        logger.debug( "REST request to search menu items" );
        Page<MenuItemDTO> page = menuItemService.searchMenuItems( keyword, pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( page, "/menu/search" );
        return new ResponseEntity<>( page, headers, HttpStatus.OK );
    }
}
