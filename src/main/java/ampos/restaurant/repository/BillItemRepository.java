package ampos.restaurant.repository;

import java.util.List;

import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.BillItemReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Long>, JpaSpecificationExecutor<BillItem> {
    @Query( "SELECT new ampos.restaurant.domain.BillItemReport( menuItem.name, sum(billItem.quantity), sum(billItem.quantity)*menuItem.price ) " + " FROM BillItem billItem" + " LEFT JOIN MenuItem menuItem" + " ON billItem.menuItem.id = menuItem.id" + " GROUP BY menuItem.id" )
    List<BillItemReport> getAllBillReport();
}
