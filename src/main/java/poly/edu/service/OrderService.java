package poly.edu.service;

import java.util.List;

import poly.edu.model.CartItemModel;
import poly.edu.model.OrderModel;
import poly.edu.model.PageModel;

public interface OrderService {
	OrderModel create(String address, List<CartItemModel> listCartModel);
	void delete(Integer id);
	List<OrderModel> getAllByPage(PageModel pageModel);
	List<OrderModel> getAllByStatus(PageModel pageModel, Integer status);
	Integer getCountByStatus(Integer status);
	List<OrderModel> getAllByUser();
	Integer getCountAll();
	OrderModel update(Integer id, Integer status);
}
