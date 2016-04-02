package pl.zt.mk.beans.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.zt.mk.services.UserService;

/**
 * Created by zt on 2016-04-02.
 */
@Component
@Scope(value = "view")
public class ChangePasswordBean {

	@Getter
	@Setter
	private String oldPassword, newPassword, repeatPassword;


	@Autowired
	UserService userService;

	public void change() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();


	}
}
