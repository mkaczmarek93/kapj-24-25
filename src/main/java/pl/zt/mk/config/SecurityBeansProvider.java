package pl.zt.mk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by zt on 2016-04-04.
 */

@Configuration
public class SecurityBeansProvider {


	@Autowired
	UserDetailsService userDetailService;

	@Autowired
	PasswordEncoder encoder;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailService);
		authProvider.setPasswordEncoder(encoder);
		return authProvider;
	}
}
