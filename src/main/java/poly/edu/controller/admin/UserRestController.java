package poly.edu.controller.admin;

import java.util.List;


import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.edu.entity.User;
import poly.edu.entity.Order;
import poly.edu.entity.User;
import poly.edu.enums.RoleName;
import poly.edu.model.UserModel;
import poly.edu.model.UserModel;
import poly.edu.model.CategoryModel;
import poly.edu.model.LoginForm;
import poly.edu.model.PageModel;
import poly.edu.repository.UserRepository;
import poly.edu.repository.OrderRepository;
import poly.edu.repository.UserRepository;
import poly.edu.security.jwt.JwtProvider;
import poly.edu.security.service.UserPrinciple;
import poly.edu.service.UserService;
import poly.edu.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequestMapping("admin/users")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class UserRestController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService service;
	
	
	@GetMapping("getAllByPage")
	public  ResponseEntity<?> getAllByPage(PageModel pageModel){
		List<UserModel> list = service.getAllByPage(pageModel);
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("getAll")
	public  ResponseEntity<?> getAll(){
		List<UserModel> list = service.getAll();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		if(!userRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping(value = "createOrUpdate")
	public ResponseEntity<?> createOrUpdate(
			@RequestBody UserModel model
		) {
		if(model.getId()!=null) {
			return ResponseEntity.ok(service.update(model));
		}
		return ResponseEntity.ok(service.create(model));
	}
}
