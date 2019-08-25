package ampos.restaurant.repository;

        import java.util.List;

        import ampos.restaurant.domain.BillItem;
        import ampos.restaurant.domain.BillItemReport;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

/**
 * Repository to manage bill item
 */
@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Long> {
    // @formatter:off
    @Query( "SELECT new ampos.restaurant.domain.BillItemReport( menuItem, sum(billItem.quantity), sum(billItem.quantity)*menuItem.price ) "
            + " FROM BillItem billItem"
            + " LEFT JOIN MenuItem menuItem"
            + " ON billItem.menuItem.id = menuItem.id"
            + " GROUP BY menuItem.id" )
    // @formatter:on
    List<BillItemReport> getAllBillReport();


    @Modifying
    @Query("DELETE"
            + " FROM BillItem billItem"
            + " WHERE billItem.bill.id = :billId")
    void deleteBillItemsByBillId(@Param("billId") Long billId);
}
