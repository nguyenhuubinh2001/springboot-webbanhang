package poly.edu.service;



import java.util.List;

import poly.edu.entity.CartItem;
import poly.edu.entity.Order;
import poly.edu.entity.Product;
import poly.edu.model.CartItemModel;
import poly.edu.model.OrderDetailModel;
import poly.edu.model.PageModel;

public interface OrderDetailService {
	void create(Order order, CartItemModel cartItemModel);
	void delete(Integer id);
}
