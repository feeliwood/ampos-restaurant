package ampos.restaurant.domain.mapper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 * 
 * @param <D>
 *            - DTO type parameter.
 * @param <E>
 *            - Entity type parameter.
 */

public interface EntityMapper<D, E> {

    /**
     * Map dto type to entity type
     * @param dto
     * @return entity
     */
    public E toEntity( D dto );

    /**
     * Map entity type to dto type
     *
     * @param entity
     * @return dto
     */
    public D toDto( E entity );

    /**
     * Map list of dto type to list of entity type
     *
     * @param dtoList
     * @return entityList
     */
    public List<E> toEntity( List<D> dtoList );

    /**
     * Map list of entity type to list of dto type
     *
     * @param entityList
     * @return dtoList
     */
    public List<D> toDto( List<E> entityList );
}