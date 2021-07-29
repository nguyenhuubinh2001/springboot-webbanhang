package poly.edu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.entity.Category;
import poly.edu.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	Page<Product> findByCategoryAndNameContaining(Category category,String name,Pageable pageable);
	Page<Product> findByNameContaining(String name,Pageable pageable);
	List<Product> findByCategoryAndNameContaining(Category category, String name);
	List<Product> findByNameContaining(String name);
	
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);
}
