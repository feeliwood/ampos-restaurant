package ampos.restaurant.service;

import ampos.restaurant.exception.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface GenericService <REQUEST, RESPONSE, ID> {
    /**
     * Create or update item
     *
     * @param id
     *            : of request item to be updated ( in case of updating item )
     * @param request
     *            : the item to be persisted
     * @return
     * @throws ApplicationException
     */
    RESPONSE save( ID id, REQUEST request ) throws ApplicationException;

    /**
     * Delete item
     *
     * @param id
     *            : of the item to be deleted
     */
    void delete( ID id ) throws ApplicationException;

    /**
     * Get item by Id
     *
     * @param id
     *            : of the item to be retrieved
     * @return
     * @throws ApplicationException
     */
    RESPONSE findById( ID id ) throws ApplicationException;

    /**
     * Get list items
     *
     * @param pageable
     *            : the pagination information
     * @return
     * @throws ApplicationException
     */
    Page<RESPONSE> findWithPageable(Pageable pageable ) throws ApplicationException;
}
