package poly.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Category;
import poly.edu.entity.Product;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
	
}
