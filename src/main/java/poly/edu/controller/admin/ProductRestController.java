package poly.edu.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.entity.Category;
import poly.edu.model.CategoryModel;
import poly.edu.model.PageModel;
import poly.edu.model.ProductModel;
import poly.edu.repository.CategoryRepository;
import poly.edu.repository.ProductRepository;
import poly.edu.service.ProductService;

@RequestMapping("admin/products")
@RestController
public class ProductRestController {
	@Autowired
	private ProductRepository repository;

	@Autowired
	ProductService service;

	@Autowired
	CategoryRepository cateRepo;

	@GetMapping("getAllByCategory")
	public ResponseEntity<?> getAllByCategory(
			PageModel pageModel,
			@RequestParam("categoryId") Optional<Integer> categoryId,
			@RequestParam("search") String search
			
		) {
		List<ProductModel> list = new ArrayList<>();
		if (categoryId.isPresent()) {
			list = service.getAllByCategoryAndName(pageModel, categoryId.get(),search);
		} else {
			list = service.getAllByPage(pageModel, search);
		}
		if (list.isEmpty()) {
			System.out.println("empty");
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(list);
	}

	@GetMapping("getCount")
	public ResponseEntity<?> getCount(
			@RequestParam("categoryId") Optional<Integer> categoryId,
			@RequestParam("search") String search
			) {
		Integer count = 0;
		if (categoryId.isPresent()) {
			count = service.getCountByCategoryAndName(categoryId.get(),search);
		} else
			count = service.getCountByName(search);
		return ResponseEntity.ok(count);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "createOrUpdate")
	public ResponseEntity<?> createOrUpdate(ProductModel model, @RequestParam("categoryId") Integer categoryId,
			@RequestParam Optional<MultipartFile> file) {
		if (model.getId() != null) {
			return ResponseEntity.ok(service.update(model, categoryId, file));
		}
		return ResponseEntity.ok(service.create(model, categoryId, file));

	}
}
