package poly.edu.serviceImpl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import poly.edu.entity.CartItem;
import poly.edu.entity.Order;
import poly.edu.entity.OrderDetail;
import poly.edu.entity.User;
import poly.edu.model.CartItemModel;
import poly.edu.model.OrderDetailModel;
import poly.edu.model.OrderModel;
import poly.edu.model.PageModel;
import poly.edu.repository.OrderDetailRepository;
import poly.edu.repository.OrderRepository;
import poly.edu.repository.UserRepository;
import poly.edu.service.OrderDetailService;
import poly.edu.utils.ObjectMapperUtils;
import poly.edu.utils.PageableUtils;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
		

	@Autowired
	ObjectMapperUtils objMapper;
	
	@Autowired
	OrderDetailRepository repository;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public void create(Order order, CartItemModel cartItemModel) {
		CartItem cartItem = objMapper.convertEntityAndDTO(cartItemModel,CartItem.class);
		OrderDetail entity = new OrderDetail();
		entity.setOrder(order);
		entity.setPrice(cartItem.getPrice());
		entity.setQuantity(cartItem.getQuantity());
		entity.setProduct(cartItem.getProduct());
		repository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	

}
