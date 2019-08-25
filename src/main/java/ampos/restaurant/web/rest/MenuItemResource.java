package ampos.restaurant.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.domain.dto.MenuItemRequestDTO;
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
 * Controller handles requests to create/delete/update/get menu items
 * 
 */
@RestController
@RequestMapping( MenuItemResource.MENU_ITEM__MAPPING )
public class MenuItemResource extends GenericResource<MenuItemRequestDTO, MenuItemDTO, Long, MenuItemService> {

    public static final String MENU_ITEM__MAPPING = "/menu-items";

    public MenuItemResource( MenuItemService menuItemService ) {
        super(menuItemService);
    }

    @Override
    String getMapping() {
        return MENU_ITEM__MAPPING;
    }

    /**
     * GET /{mapping}/search?keyword=<keyword> : Search items by keyword.
     *
     * @param keyword:
     *            the keyword to search
     * @param pageable:
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of items in body
     */
    @ApiOperation( value = "Search menu items by title or description or additional details", response = ResponseEntity.class )
    @GetMapping( "/search" )
    public ResponseEntity<Page<MenuItemDTO>> search( @RequestParam( value = "keyword" ) String keyword, Pageable pageable ) throws ApplicationException {
        Page<MenuItemDTO> page = this.service.search( keyword, pageable );
        return new ResponseEntity<>( page, null, HttpStatus.OK );
    }
}
