package ampos.restaurant.repository;

import ampos.restaurant.domain.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillOrderRepository extends JpaRepository<Bill, Long> {

}
