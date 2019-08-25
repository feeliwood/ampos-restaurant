package ampos.restaurant.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.GenericService;
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

public abstract class GenericResource <REQUEST, RESPONSE, ID, SERVICE extends GenericService<REQUEST, RESPONSE, ID>>  {

    protected SERVICE service;

    public GenericResource( SERVICE service ) {
        this.service = service;
    }

    abstract String getMapping();

    /**
     * POST /{mapping}: Create new item
     *
     * @param request:
     *            contain data about the menu item to be created
     * @return the ResponseEntity with status 200 (OK)
     * @throws ApplicationException
     * @throws URISyntaxException
     */
    @PostMapping
    public ResponseEntity<RESPONSE> create( @RequestBody REQUEST request ) throws ApplicationException, URISyntaxException {
        RESPONSE response = this.service.save(null, request);
        return ResponseEntity.created(new URI(getMapping() + "/")).body(response);
    }

    /**
     * PUT /{mapping} : Updates an existing item.
     *
     * @param id:
     *           if of the item to update
     * @param request:
     *           item to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated item,
     */
    @PutMapping( "/{id}" )
    public ResponseEntity<RESPONSE> updateMenuItem( @PathVariable ID id, @RequestBody REQUEST request) throws ApplicationException {
        RESPONSE response = this.service.save(id, request);
        return ResponseEntity.ok().body(response);
    }

    /**
     * GET /{mapping}/:id : Get the "id" menu item.
     *
     * @param id:
     *            the id of the item to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the item, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<RESPONSE> getMenuItem(@PathVariable ID id) throws ApplicationException {
        RESPONSE response = this.service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    /**
     * GET /{mapping} : Get all the menu items.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menu
     *         items in body
     */
    @GetMapping
    public ResponseEntity<Page<RESPONSE>> getAllMenuItems(Pageable pageable) throws ApplicationException {
        Page<RESPONSE> page = this.service.findWithPageable(pageable);
        return new ResponseEntity<>(page, null , HttpStatus.OK);
    }

    /**
     * DELETE /{mapping}/:id : Delete the "id"  item.
     *
     * @param id:
     *            the id of the item to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> delete( @PathVariable ID id ) throws ApplicationException {
        this.service.delete( id );
        return ResponseEntity.ok().build();
    }
}
