package poly.edu.model;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PageModel {
	private int pageNumber = 0;
	private int pageSize = 5;
	private Sort.Direction sortDirection;
	private String sortBy;
	
}
