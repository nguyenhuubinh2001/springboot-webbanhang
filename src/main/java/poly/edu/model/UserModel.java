package poly.edu.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.entity.Order;
import poly.edu.entity.Role;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	private Integer id;
	private String username;
	private String fullname;
	private String password;
	private String passwordConfirm;
	private String email;
	private String photo;
	private List<RoleModel> roles;
	

}
