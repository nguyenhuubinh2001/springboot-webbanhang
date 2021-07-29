package poly.edu.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.edu.entity.Order;
import poly.edu.model.OrderModel;
import poly.edu.model.PageModel;
import poly.edu.model.ProductModel;
import poly.edu.repository.OrderRepository;
import poly.edu.service.OrderService;

@RequestMapping("admin/order")
@RestController
public class OrderController {

	@Autowired
	OrderService service;
	
	@Autowired
	OrderRepository repository;

	@GetMapping("getAllByStatus")
	public ResponseEntity<?> getAllByStatus(PageModel pageModel, @RequestParam("status") Optional<Integer> status

	) {
		List<OrderModel> listModel = new ArrayList<>();
		if (status.isPresent()) {
			listModel = service.getAllByStatus(pageModel, status.get());
		} else {
			listModel = service.getAllByPage(pageModel);
		}
		if (listModel.isEmpty()) {
			System.out.println("empty");
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listModel);
	}
	
	

	@GetMapping("update/{id}")
	public ResponseEntity<?> update(
			@PathVariable("id") Integer id,
			@RequestParam("status") Integer status
		) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(service.update(id, status));
	}

	@GetMapping("getCount")
	public ResponseEntity<?> getCount(@RequestParam("status") Optional<Integer> status) {
		Integer count = 0;
		if (status.isPresent()) {
			count = service.getCountByStatus(status.get());
		} else
			count = service.getCountAll();
		return ResponseEntity.ok(count);
	}

}
