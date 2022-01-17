package com.gov.wesagnkunet.lib.auth.di;

import java.util.Arrays;
import java.util.List;

import com.gov.wesagnkunet.lib.auth.UserManager;
import com.gov.wesagnkunet.lib.auth.controllers.handlers.WesagnKunetAuthenticationSuccessHandler;
import com.gov.wesagnkunet.lib.auth.data.models.User.Role;
import com.gov.wesagnkunet.lib.auth.data.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AuthConfig {


	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository){

		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepository.findByUsername(username);
			}

		};

	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler(){
		return new WesagnKunetAuthenticationSuccessHandler();
	}


	@Bean
	public List<String> clientOnlyPatterns(){
		return Arrays.asList( //TODO: ADD MORE
			"/account/"
		);
	}

	@Bean
	public List<String> adminOnlyPatterns(){
		return Arrays.asList(
			"/admin/dashboard/"
		);
	}

	@Bean
	public UserManager userManager(UserRepository repository, PasswordEncoder encoder){
		return new UserManager(repository, encoder);
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(
		HttpSecurity httpSecurity,
		@Qualifier("clientOnlyPatterns") List<String> clientOnlyPatterns,
		@Qualifier("adminOnlyPatterns") List<String> adminOnlyPatterns
	) throws Exception{
		return httpSecurity.authorizeRequests()
							.antMatchers(clientOnlyPatterns.toArray(new String[0])).hasRole(Role.CLIENT.name())
							.antMatchers(adminOnlyPatterns.toArray(new String[0])).hasRole(Role.ADMIN.name())
							.antMatchers("/", "/**").permitAll()
							.and()
							.formLogin()
							.loginPage("/auth/login")
							.successHandler(authenticationSuccessHandler())
							.and()
							.build();

	}


}
