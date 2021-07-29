package poly.edu.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.edu.entity.Order;
import poly.edu.entity.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderDetailModel {
	private Integer id;
	
	private Integer price;
	
	private Integer quantity;
	
	private ProductModel product;
		
	
}
