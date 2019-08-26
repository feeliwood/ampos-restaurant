package ampos.restaurant.service.impl;

import ampos.restaurant.domain.DomainEntity;
import ampos.restaurant.domain.mapper.GenericMapper;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.service.GenericService;
import ampos.restaurant.util.Constants;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Transactional
public abstract class GenericServiceImpl <REQUEST, RESPONSE extends Serializable, ID,
                                        ENTITY extends DomainEntity<ID>, REPOSITORY extends JpaRepository<ENTITY, ID>,
                                        MAPPER extends GenericMapper<RESPONSE, ENTITY, REQUEST>> implements GenericService <REQUEST, RESPONSE, ID> {
    protected REPOSITORY repository;
    protected MAPPER mapper;

    public GenericServiceImpl(REPOSITORY repository, MAPPER mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create or update menu item
     *
     * @param id
     *            : of request item to be updated ( in case of updating menu
     *            item )
     * @param request
     *            : the menu item to be persisted
     * @return
     * @throws ApplicationException
     */
    @Transactional
    @Override
    public RESPONSE save( ID id, REQUEST request ) throws ApplicationException {
        ENTITY existingEntity = null;
        ENTITY entityToBeUpdated = mapper.requestToEntity(request);

        if( id != null ){
            existingEntity = repository.findById(id).orElseThrow( () -> new ApplicationContextException( Constants.ITEM_NOT_FOUND ));
            mergeExistingAndNewEntity(existingEntity, entityToBeUpdated);
        }

        processBeforeSaving(request, entityToBeUpdated);
        entityToBeUpdated = repository.save(entityToBeUpdated);
        return mapper.entityToDto(entityToBeUpdated);
    }

    void mergeExistingAndNewEntity(ENTITY existingEntity, ENTITY newEntity) { }

    void processBeforeSaving(REQUEST request, ENTITY entity) {}

    /**
     * Delete item
     *
     * @param id
     *            : of the item to be deleted
     */
    @Override
    public void delete( ID id ) throws ApplicationException { }

    /**
     * Get menu item by Id
     *
     * @param id
     *            : of the menu item to be retrieved
     * @return
     * @throws ApplicationException
     */
    @Override
    @Transactional(readOnly = true)
    public RESPONSE findById( ID id ) throws ApplicationException {
        ENTITY entity = repository.findById(id).orElseThrow( () -> new ApplicationException( Constants.ITEM_NOT_FOUND ));
        return mapper.entityToDto(entity);
    }

    /**
     * Get list menu items
     *
     * @param pageable
     *            : the pagination information
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RESPONSE> findWithPageable(Pageable pageable ) {
        Page<ENTITY> page = repository.findAll(pageable);
        return page.map(mapper::entityToDto);
    }
}
