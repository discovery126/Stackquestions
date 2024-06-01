package ru.denis.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class StackQuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackQuestionsApplication.class, args);
	}


//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//		http.oauth2Login(Customizer.withDefaults());
//		return http
//				.authorizeHttpRequests(c->c.requestMatchers("/error").permitAll()
//						.requestMatchers("/users").hasRole("ADMIN")
//						.anyRequest().authenticated())
//				.build();
//	}

//	@Bean
//	public JwtAuthenticationConverter jwtAuthenticationConverter() {
//		var converter = new JwtAuthenticationConverter();
//		var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//		converter.setPrincipalClaimName("preferred_username");
//		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//			var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
//			var roles = jwt.getClaimAsStringList("spring_sec_roles");
//
//			return Stream.concat(authorities.stream(),
//					roles.stream().filter(role -> role.startsWith("ROLE_"))
//							.map(SimpleGrantedAuthority::new)
//							.map(GrantedAuthority.class::cast))
//					.toList();
//		});
//
//		return converter;
//	}
//
//	@Bean
//	public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
//		var oidcUserService = new OidcUserService();
//		return userRequest -> {
//			var oidcUser = oidcUserService.loadUser(userRequest);
//			var roles = oidcUser.getClaimAsStringList("spring_sec_roles");
//			var authorities = Stream.concat(oidcUser.getAuthorities().stream(),
//							roles.stream().filter(role -> role.startsWith("ROLE_"))
//									.map(SimpleGrantedAuthority::new)
//									.map(GrantedAuthority.class::cast))
//					.toList();
//			return new DefaultOidcUser(authorities,oidcUser.getIdToken(),oidcUser.getUserInfo());
//		};
//	}
}
