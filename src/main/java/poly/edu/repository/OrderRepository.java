package poly.edu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Order;
import poly.edu.entity.User;


public interface OrderRepository extends JpaRepository<Order,Integer> {
	List<Order> findByUser(User user);
	Page<Order> findByStatus(Integer status, Pageable pageable);
	List<Order> findByStatus(Integer status);
}
