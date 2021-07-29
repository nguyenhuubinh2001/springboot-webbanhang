package poly.edu.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.model.ProductModel;
import poly.edu.service.ProductService;

@RequestMapping("/thymlef/product")
@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
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
}
