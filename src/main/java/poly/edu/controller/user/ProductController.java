package poly.edu.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import poly.edu.entity.Order;
import poly.edu.model.ProductModel;
import poly.edu.repository.OrderRepository;
import poly.edu.service.ProductService;

@RequestMapping("/thymlef/product")
@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	OrderRepository orderRepo;
	
	@GetMapping("list")
	public String list(Model model,@RequestParam("cid") Optional<Integer> cid) {
		if(cid.isPresent()) {
			List<ProductModel> list = service.findByCategoryId(cid.get());
			model.addAttribute("items",list);
		}
		else {
			List<ProductModel> list = service.getAll();
			
			
			model.addAttribute("items",list);
		}
		
		return "product/list";
	}
	
	@GetMapping("detail/{id}")
	public String detail() {
		return "product/detail";
	}
	
	@ResponseBody
	@GetMapping("test")
	public ResponseEntity<?> list1(Model model,@RequestParam("cid") Optional<Integer> cid) {
		List<Object> doanhthu = orderRepo.getSaleByYear(2021);
		return ResponseEntity.ok(doanhthu);
	}
}
