package ampos.restaurant.repository;

import java.util.Collection;
import java.util.List;

import ampos.restaurant.domain.MenuItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage menu item
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // @formatter:off
    @Query( "SELECT distinct menuItem FROM MenuItem AS menuItem WHERE (:keyword is NULL) "
    		+ " OR (LOWER(menuItem.name) LIKE CONCAT('%',:keyword,'%'))"
    		+ " OR (LOWER(menuItem.description) LIKE CONCAT('%',:keyword,'%'))"
    		+ " OR (LOWER(menuItem.details) LIKE CONCAT('%',:keyword,'%'))")
    // @formatter:on
    Page<MenuItem> search( @Param( "keyword" ) String keyword, Pageable pageable );

    List<MenuItem> findMenusByIdIn(Collection<Long> ids);

    @Modifying
    @Query("DELETE FROM MenuItem WHERE id in :ids")
    void deleteAllMenuItemWithIds(@Param("ids") Collection<Long> ids);
}
