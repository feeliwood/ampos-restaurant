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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Menu management controller is used to CRUD menu item.
 * 
 */
@RestController
@RequestMapping( MenuItemController.CONTROLLER_MAPPING )
public class MenuItemController {

    public static final String CONTROLLER_MAPPING = "/menu";
    private static final String ENTITY_NAME = "menu-item";
    public static final String EMPTY = "";

    private static final Logger logger = LoggerFactory.getLogger( MenuItemController.class );

    private MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    /**
     * Create menu item
     * 
     * @param file : the image of menu-item
     * @Param menuItemDTO: contain data about the menu item to be created
     * @throws ApplicationException
     */
    //@PostMapping(  consumes = { "multipart/form-data", "application/json" } )
    @PostMapping(value = "/items", headers = {"content-type=multipart/form-data"})
    public ResponseEntity<MenuItemDTO> createMenuItem( @RequestParam( value = "item" ) MenuItemDTO menuItemDTO, @RequestParam( value = "file", required = false ) MultipartFile file ) throws ApplicationException, URISyntaxException {
        if ( logger.isDebugEnabled() ) {
            logger.debug( "Creating menu item...." );
        }

        if(menuItemDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new menu item cannot already have an ID")).body(null);
        }

        MenuItemDTO result = menuItemService.save(menuItemDTO, file);
        return ResponseEntity.created(new URI("/menu/items/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                        .body(result);
    }

    /**
     * PUT  /items : Updates an existing menu item.
     *
     * @param menuItemDTO the clientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientDTO,
     */
    @PutMapping(value = "/items/{id}", headers = {"content-type=multipart/form-data"})
    public ResponseEntity<MenuItemDTO> updateMenuItem(@PathVariable Long id,  @RequestParam( value = "item" ) MenuItemDTO menuItemDTO, @RequestParam( value = "file", required = false ) MultipartFile file) throws ApplicationException, URISyntaxException {
        logger.debug("REST request to update Menu Item : {}", menuItemDTO);

        MenuItemDTO result = menuItemService.update(id, menuItemDTO, file);
        return ResponseEntity.ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                        .body(result);
    }


    /**
     * GET  /items/:id : get the "id" menu item.
     *
     * @param id the id of the menuItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) throws ApplicationException {
        logger.debug("REST request to get Menu Item : {}", id);
        MenuItemDTO menuItemDTO = menuItemService.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(menuItemDTO));
    }


    /**
     * GET  /items : get all the menu items.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menu items in body
     */
    @GetMapping("/items")
    public ResponseEntity<Page<MenuItemDTO>> getAllMenuItems(Pageable pageable) throws ApplicationException {
        logger.debug("REST request to get a page of Menu Items");
        Page<MenuItemDTO> page = menuItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/menu/items");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }


    /**
     * DELETE  /items/:id : delete the "id" menu item.
     *
     * @param id the id of the menuItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) throws ApplicationException {
        logger.debug("REST request to delete menu item : {}", id);
        menuItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
