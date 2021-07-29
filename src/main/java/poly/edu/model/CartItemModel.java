package poly.edu.model;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.entity.Product;
import poly.edu.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CartItemModel {
	private Integer id;
	
	private Integer quantity;
	
	private Integer price;
	
	private ProductModel product;
	
}
