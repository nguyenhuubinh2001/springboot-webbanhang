package poly.edu.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.entity.CartItem;
import poly.edu.entity.Product;
import poly.edu.entity.User;
import poly.edu.model.CartItemModel;
import poly.edu.repository.CartItemRepository;
import poly.edu.repository.ProductRepository;
import poly.edu.repository.UserRepository;
import poly.edu.service.CartItemService;
import poly.edu.utils.ObjectMapperUtils;

@Service
public class CartItemServiceImpl implements CartItemService  {
	
	@Autowired
	ObjectMapperUtils objMapper;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CartItemRepository repository;

	@Autowired
	UserRepository userRepository;
		
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<CartItemModel> getByUser(String username) {
		User user = userRepository.findByUsername(username);
		List<CartItem> list = repository.findByUser(user);
		List<CartItemModel> listModel = objMapper.mapAll(list,CartItemModel.class);
		return listModel;
	}

	

	@Override
	public CartItemModel save(CartItemModel model) {
		String username = (String) request.getSession().getAttribute("username");
		User user = userRepository.findByUsername(username);
		
		Product product = objMapper.convertEntityAndDTO(model.getProduct(),Product.class);
		CartItem cartItem = repository.findByUserAndProduct(user,product);
		if(cartItem!=null) {
			cartItem.setQuantity(cartItem.getQuantity()+1);
			cartItem.setPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
			cartItem.setUser(user);
			repository.save(cartItem);
		}else {
			CartItem entity = objMapper.convertEntityAndDTO(model,CartItem.class);
			entity.setPrice(entity.getQuantity()*entity.getProduct().getPrice());
			entity.setUser(user);
			repository.save(entity);
		}
		return model;
	}

	@Override
	public CartItemModel update(CartItemModel model) {
		String username = (String) request.getSession().getAttribute("username");
		User user = userRepository.findByUsername(username);
		CartItem entity = objMapper.convertEntityAndDTO(model,CartItem.class);
		entity.setUser(user);
		entity.setPrice(model.getProduct().getPrice()*model.getQuantity());
		repository.save(entity);
		return model;
	}
	
}
