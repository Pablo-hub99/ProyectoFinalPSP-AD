package org.jesuitasrioja.ProyectoCovid.modelo.user;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends UserEntity {
	private String token;
	@Builder(builderMethodName = "jwtUserResponseBuilder")
	public JwtUserResponse(String username, String email, Set<UserRole> roles, String token) {
		this.setUsername(username);
		this.setRoles(roles);
		this.token = token;
	}
}
