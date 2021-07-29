package poly.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.CartItem;
import poly.edu.entity.Product;
import poly.edu.entity.User;
import poly.edu.model.CartItemModel;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	List<CartItem> findByUser(User user);
	CartItem findByUserAndProduct(User user, Product product);
	
}
