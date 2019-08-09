package ampos.restaurant.specitifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ampos.restaurant.domain.MenuItem;

import org.springframework.data.jpa.domain.Specification;

/**
 * MenuSearchSpecifications is used to search keyword for menu
 *
 */
public class MenuSearchSpecifications { // implements Specification<MenuItem> {
//    private String keyword;
//
//    public MenuSearchSpecifications( String keyword ) {
//        this.keyword = keyword;
//    }
//
//    @Override
//    public Predicate toPredicate( Root<MenuItem> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
//        String keySearchPattern = "%" + keyword + "%";
//        return searchKeyword( root, cb, keySearchPattern );
//    }
//
//    /**
//     * Search keywords
//     *
//     * @param root
//     * @param cb
//     * @return
//     */
//    private Predicate searchKeyword( Root<MenuItem> root, CriteriaBuilder cb, String keySearchPattern ) {
//        // @formatter:off
//        Predicate predicate = cb.or( cb.like( root.get( Item_.title ), keySearchPattern),
//                cb.like( root.get( Item_.description ), keySearchPattern),
//                cb.like( root.get( Item_.details ), keySearchPattern) );
//        // @formatter:on
//        return predicate;
//    }
}
