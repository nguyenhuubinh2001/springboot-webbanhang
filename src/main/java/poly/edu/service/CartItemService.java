package poly.edu.service;

import java.util.List;

import poly.edu.entity.User;
import poly.edu.model.CartItemModel;

public interface CartItemService {
	void delete(Integer id);
	List<CartItemModel> getByUser(String username);
	CartItemModel update(CartItemModel model);
	CartItemModel save(CartItemModel cartItem);
}
