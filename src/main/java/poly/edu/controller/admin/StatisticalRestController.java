package poly.edu.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.edu.repository.OrderRepository;
import poly.edu.service.StatisticalService;

@RequestMapping("admin/statistical")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class StatisticalRestController {
	
	@Autowired
	StatisticalService service;
	
	@GetMapping("getSales")
	public ResponseEntity<?> getSaleByYear(@RequestParam("year") Optional<Integer> year){
		if(year.isPresent()) {
			return ResponseEntity.ok(service.getSales(year.get()));
		}
		return ResponseEntity.ok(service.getSales(2021));
	}
	
	
}
