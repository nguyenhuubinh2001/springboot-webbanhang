package poly.edu.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.enums.RoleName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RoleModel {
	private Integer id;
	
	private RoleName name;
}
