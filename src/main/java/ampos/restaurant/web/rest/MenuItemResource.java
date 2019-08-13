package ampos.restaurant.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.MenuItemService;


import ampos.restaurant.web.rest.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private static final Logger logger = LoggerFactory.getLogger( MenuItemResource.class );

    private MenuItemService menuItemService;

    public MenuItemResource( MenuItemService menuItemService ) {
        this.menuItemService = menuItemService;
    }

    /**
     * POST /items: Create menu item
     * 
     * @param menuItemDTO:
     *            contain data about the menu item to be created
     * @return the ResponseEntity with status 200 (OK)
     * @throws ApplicationException
     * @throws URISyntaxException
     */
    @ApiOperation( value = "Create a new menu item", response = ResponseEntity.class )
    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem( @RequestBody MenuItemDTO menuItemDTO ) throws ApplicationException, URISyntaxException {
    	logger.debug( "REST request to create menu item" );

        MenuItemDTO result = menuItemService.createOrUpdateMenuItem(null, menuItemDTO);
        return ResponseEntity.created(new URI(MENU_ITEM__MAPPING + "/" + result.getId())).body(result);
    }

    /**
     * PUT /items : Updates an existing menu item.
     *
     * @param menuItemDTO:
     *            the clientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientDTO,
     */
    @ApiOperation( value = "Updates an existing menu item", response = ResponseEntity.class )
    @PutMapping( "/{id}" )
    public ResponseEntity<MenuItemDTO> updateMenuItem( @PathVariable Long id, @RequestBody MenuItemDTO menuItemDTO ) throws ApplicationException {
        logger.debug( "REST request to update menu item : {}", menuItemDTO );

        MenuItemDTO result = menuItemService.createOrUpdateMenuItem(id, menuItemDTO);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET /items/:id : Get the "id" menu item.
     *
     * @param id:
     *            the id of the menuItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuItemDTO, or with status 404 (Not Found)
     */
    @ApiOperation( value = "Get the menu item with id", response = ResponseEntity.class )
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) throws ApplicationException {
        logger.debug("REST request to get menu item : {}", id);
        MenuItemDTO menuItemDTO = menuItemService.findMenuItemById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(menuItemDTO));
    }

    /**
     * GET /items : Get all the menu items.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menu
     *         items in body
     */
    @ApiOperation( value = "Get all the menu items", response = ResponseEntity.class )
    @GetMapping
    public ResponseEntity<Page<MenuItemDTO>> getAllMenuItems(Pageable pageable) throws ApplicationException {
        logger.debug("REST request to get a page of menu items");
        Page<MenuItemDTO> page = menuItemService.findAllMenuItems(pageable);
        return new ResponseEntity<>(page, null , HttpStatus.OK);
    }

    /**
     * DELETE /items/:id : Delete the "id" menu item.
     *
     * @param id:
     *            the id of the menuItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation( value = "Delete a menu item with id", response = ResponseEntity.class )
    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteMenuItem( @PathVariable Long id ) {
        logger.debug( "REST request to delete menu item : {}", id );
        menuItemService.deleteMenuItem( id );
        return ResponseEntity.ok().build();
    }

    /**
     * GET /items/search?keyword=<keyword> : Search menu items by keyword.
     *
     * @param keyword:
     *            the keyword of menu title or description or additional
     *            details.
     * @param pageable:
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menu
     *         items in body
     */
    @ApiOperation( value = "Search menu items by title or description or additional details", response = ResponseEntity.class )
    @GetMapping( "/search" )
    public ResponseEntity<Page<MenuItemDTO>> searchMenuItems( @RequestParam( value = "keyword" ) String keyword, Pageable pageable ) throws ApplicationException {
        logger.debug( "REST request to search menu items" );
        Page<MenuItemDTO> page = menuItemService.searchMenuItems( keyword, pageable );
        return new ResponseEntity<>( page, null, HttpStatus.OK );
    }
}
