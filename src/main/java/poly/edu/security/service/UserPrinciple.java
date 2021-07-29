package poly.edu.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import poly.edu.entity.Role;
import poly.edu.entity.User;

@Data
public class UserPrinciple implements UserDetails {
	
	private Integer id;

    private String name;

    private String username;

    private String email;
    
	private String fullname;
	
    @JsonIgnore
    private String password;

    
	private Collection<? extends GrantedAuthority> roles;
	public UserPrinciple(Integer id, String name,
            String username, String email, String fullname, String password,
            Collection<? extends GrantedAuthority> roles) {
		this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.roles = roles;
	}
	public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
        return new UserPrinciple(
        		user.getId(),
        		user.getFullname(),
        		user.getUsername(),
        		user.getEmail(),
        		user.getFullname(),
        		user.getPassword(),
        		authorities
        );
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
