package poly.edu.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import poly.edu.entity.Order;
import poly.edu.entity.OrderDetail;
import poly.edu.entity.User;
import poly.edu.model.CartItemModel;
import poly.edu.model.OrderModel;
import poly.edu.model.PageModel;
import poly.edu.repository.OrderRepository;
import poly.edu.repository.UserRepository;
import poly.edu.service.CartItemService;
import poly.edu.service.OrderDetailService;
import poly.edu.service.OrderService;
import poly.edu.utils.ObjectMapperUtils;
import poly.edu.utils.PageableUtils;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Autowired
	OrderRepository repository;
	
	@Autowired
	OrderDetailService orderDetailsService;
	
	@Autowired
	CartItemService cartItemService;
	
	@Override
	public OrderModel create(String address, List<CartItemModel> listCartModel) {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		User user = userRepository.findByUsername(username);
		Order order = new Order();
		order.setUser(user);
		order.setAddress(address);
		order.setCreateDate(new Date());
		order.setStatus(0);
		Order orderSave = repository.save(order);
		for (CartItemModel cartItemModel : listCartModel) {
			orderDetailsService.create(orderSave, cartItemModel);
			cartItemService.delete(cartItemModel.getId());
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		Optional<Order> option = repository.findById(id);
		if(option.isPresent()) {
			for (OrderDetail orderDetai : option.get().getOrderDetails()) {
				orderDetailsService.delete(orderDetai.getId());
			}
			repository.deleteById(id);
		}
	}
	
	@Override
	public List<OrderModel> getAllByUser() {
		String username = (String) request.getSession().getAttribute("username");
		User user = userRepository.findByUsername(username);
		List<Order> list = repository.findByUser(user);
		List<Order> listNew = new ArrayList<Order>();
		for (Order order : list) {
			if(order.getStatus()<100) {
				listNew.add(order);
			}
		}
		List<OrderModel> listModel = objectMapper.mapAll(listNew, OrderModel.class);
		return listModel;
	}
	
	@Override
	public List<OrderModel> getAllByPage(PageModel pageModel) {
		Pageable pageable = PageableUtils.createPageable(pageModel);
		List<Order> list = repository.findAll(pageable).getContent();
		List<OrderModel> listModel = objectMapper.mapAll(list, OrderModel.class);
		return listModel;
	}

	@Override
	public List<OrderModel> getAllByStatus(PageModel pageModel, Integer status) {
		Pageable pageable = PageableUtils.createPageable(pageModel);
		List<Order> list = repository.findByStatus(status,pageable).getContent();
		List<OrderModel> listModel = objectMapper.mapAll(list, OrderModel.class);
		return listModel;
	}

	@Override
	public Integer getCountByStatus(Integer status) {
		List<Order> list = repository.findByStatus(status);
		return list.size();
	}

	@Override
	public Integer getCountAll() {
		return (int) repository.count();
	}

	@Override
	public OrderModel update(Integer id, Integer status) {
		Order entity = repository.findById(id).get();
		entity.setStatus(status);
		return objectMapper.convertEntityAndDTO(repository.save(entity),OrderModel.class);
	}

	

}
