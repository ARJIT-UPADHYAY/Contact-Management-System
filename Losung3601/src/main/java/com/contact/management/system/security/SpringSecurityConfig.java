
package com.contact.management.system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserDetailsService userService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); // set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
		return auth;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer
				.requestMatchers(HttpMethod.POST, "/contacts/createContact").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/contacts/getAllContact").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.GET, "/contacts/findByFirstName/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.GET, "/contacts/findByLastName/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.GET, "/contacts/findByEmail/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.GET, "/contacts/findByPhoneNumber/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.GET, "/contacts/findByEmail/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.PUT, "/contacts/updateListOfContacts/**").hasRole("MANAGER")
				.requestMatchers(HttpMethod.POST, "/contacts/createListOfContacts").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/contacts/updateContact").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/contacts/**").hasRole("ADMIN"));

		// use HTTP Basic authentication
		http.httpBasic();

		// disable Cross Site Request Forgery (CSRF)
		http.csrf().disable();

		return http.build();
	}
}
