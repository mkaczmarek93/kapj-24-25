package pl.zt.mk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by zt on 2016-03-21.
 */
@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityConfigMock extends WebSecurityConfigurerAdapter {
	private final static String ADMIN = "ADMIN";
	private final static String USER = "USER";

	@Autowired
	DaoAuthenticationProvider authProvider;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http
				.authorizeRequests()
				.and()
				.formLogin()
				.loginPage("/views/login.xhtml")
				.defaultSuccessUrl("/index.xhtml")
				.permitAll()
				.and().exceptionHandling().accessDeniedPage("/views/403-error.xhtml")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/views/logout.xhtml")).logoutSuccessUrl("/views/login.xhtml?logged_out").invalidateHttpSession(true).permitAll();
	}


	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROLE_ADMIN");
		auth.authenticationProvider(authProvider);

	}
}
