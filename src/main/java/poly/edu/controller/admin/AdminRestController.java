package poly.edu.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.edu.model.PageModel;
import poly.edu.model.UserModel;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AdminRestController {
	
	@GetMapping("admin")
	public  ResponseEntity<?> admin(){
		return ResponseEntity.ok().build();
	}
}
