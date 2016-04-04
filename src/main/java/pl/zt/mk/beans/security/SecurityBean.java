package pl.zt.mk.beans.security;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by zt on 2016-04-03.
 */
@Component
@Scope(value = "session")
public class SecurityBean {

	private final static String ROLE = "ROLE_";
	private final static String ANONYMOUS = "ROLE_ANONYMOUS";

	public boolean hasRole(String role) {
		SecurityContext contextHolder = SecurityContextHolder.getContext();
		if (Objects.nonNull(contextHolder) && Objects.nonNull(contextHolder.getAuthentication())) {
			Collection<? extends GrantedAuthority> roles = contextHolder
					.getAuthentication()
					.getAuthorities();

			for (GrantedAuthority r : roles) {
				if (r.getAuthority().equals(parse(role)))
					return true;
			}

		}

		return false;
	}

	public boolean isAuthenticated() {
		SecurityContext contextHolder = SecurityContextHolder.getContext();
		return Objects.nonNull(contextHolder)
				&& Objects.nonNull(contextHolder.getAuthentication())
				//lista authorities zawsze posiada element ROLE_ANONYMOUS
				&& !contextHolder.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(ANONYMOUS));
	}

	private String parse(String role) {
		role = role.toUpperCase();
		if (!role.startsWith(ROLE))
			role = ROLE.concat(role);

		return role;
	}

}
