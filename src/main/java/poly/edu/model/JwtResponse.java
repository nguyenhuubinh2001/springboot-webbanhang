package poly.edu.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JwtResponse {
	private Integer id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String fullname;
    private String email;
    private List<String> roles;
    
    public JwtResponse(String accessToken, Integer id, String username, String name, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.fullname = name;
        this.email = email;
    }
}
