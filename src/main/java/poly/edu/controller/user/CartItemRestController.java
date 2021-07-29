package poly.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.edu.model.CartItemModel;
import poly.edu.repository.CartItemRepository;
import poly.edu.service.CartItemService;

@RequestMapping("user/cartItem")
@RestController
public class CartItemRestController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	CartItemService service;

	@Autowired
	CartItemRepository repository;
	
	@GetMapping("getCart")
	public ResponseEntity<?> getCarts() {
		String username = (String) request.getSession().getAttribute("username");
		List<CartItemModel> listModel = service.getByUser(username);
		if (listModel.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listModel);
	}

	@PostMapping("saveCart")
	public ResponseEntity<?> save(
			@RequestBody CartItemModel cartItemModel
			) {
		CartItemModel model = service.save(cartItemModel);
		return ResponseEntity.ok(model);

	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("update")
	public ResponseEntity<?> update(
			@RequestBody CartItemModel model
		){
		CartItemModel cartItemModel = service.update(model);
		return ResponseEntity.ok(cartItemModel);
	}

}
