package poly.edu.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.model.CategoryModel;
import poly.edu.model.ProductModel;
import poly.edu.service.CategoryService;

@RequestMapping("/thymlef/category")
@Controller
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	@GetMapping("list")
	public String list(Model model) {
		List<CategoryModel> list = service.getAll();
		model.addAttribute("items",list);
		return "category/list";
	}
}
