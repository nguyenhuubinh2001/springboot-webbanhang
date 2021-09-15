package poly.edu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.entity.Order;
import poly.edu.entity.User;


public interface OrderRepository extends JpaRepository<Order,Integer> {
	List<Order> findByUser(User user);
	Page<Order> findByStatus(Integer status, Pageable pageable);
	List<Order> findByStatus(Integer status);
	
	@Query("SELECT MONTH(o.createDate) , SUM(od.price)  FROM Order o JOIN OrderDetail od ON o.id = od.order.id WHERE YEAR(o.createDate) = :cyear and o.status=100 GROUP BY MONTH(o.createDate)")
	List<Object> getSaleByYear(@Param("cyear") Integer year);
}
