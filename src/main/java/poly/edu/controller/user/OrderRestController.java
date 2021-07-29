package poly.edu.controller.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.edu.model.CartItemModel;
import poly.edu.model.OrderModel;
import poly.edu.repository.OrderRepository;
import poly.edu.service.OrderService;

@RequestMapping("user/order")
@RestController
public class OrderRestController {
	@Autowired
	OrderService service;
	
	@Autowired
	OrderRepository repository;
	
	@PostMapping("save")
	public ResponseEntity<?> saveOrder(
			@RequestBody String jsonAsString,
			@RequestParam("address") String address
		) throws JsonMappingException, JsonProcessingException {
		JSONObject j1 = JSONObject.parseObject(jsonAsString);
		List<CartItemModel> listModel = JSONObject.parseArray(j1.getJSONArray("dataOrder").toJSONString(), 
				CartItemModel.class);
		OrderModel model = service.create(address, listModel);
		return ResponseEntity.ok(model);	
	}
	
	@GetMapping("getAllByUser")
	public ResponseEntity<?> getAllByUser() {
		List<OrderModel> listModel = service.getAllByUser();
		if (listModel.isEmpty()) {
			System.out.println("empty");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listModel);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
