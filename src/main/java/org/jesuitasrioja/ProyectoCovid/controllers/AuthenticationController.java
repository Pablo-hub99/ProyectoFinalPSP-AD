package org.jesuitasrioja.ProyectoCovid.controllers;

import org.jesuitasrioja.ProyectoCovid.configurations.security.jwt.JwtTokenProvider;
import org.jesuitasrioja.ProyectoCovid.modelo.user.GetUserDTO;
import org.jesuitasrioja.ProyectoCovid.modelo.user.JwtUserResponse;
import org.jesuitasrioja.ProyectoCovid.modelo.user.LoginRequest;
import org.jesuitasrioja.ProyectoCovid.modelo.user.UserDTOConverter;
import org.jesuitasrioja.ProyectoCovid.modelo.user.UserEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UserDTOConverter converter;

	@PostMapping("/auth/login")
	
	public JwtUserResponse login(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()
			));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		UserEntity user = (UserEntity) authentication.getPrincipal();
		String jwtToken = tokenProvider.generateToken(authentication);
			return convertUserEntityAndTokenToJwtUserResponse(user, jwtToken);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user/me")
	public GetUserDTO me(@AuthenticationPrincipal UserEntity user) {
		return converter.convertUserEntityToGetUserDto(user);
	}
	private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(UserEntity user, String jwtToken) {
		return JwtUserResponse.jwtUserResponseBuilder().username(user.getUsername()).roles(user.getRoles())
				.token(jwtToken).build();
	}
}
