package poly.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Role;
import poly.edu.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role,Integer> {
	Role findByName(RoleName name);
}
