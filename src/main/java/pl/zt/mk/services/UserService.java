package pl.zt.mk.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.entity.meta.Authorities;

import java.util.List;

/**
 * Created by zt on 2016-03-22.
 */
@Service
public interface UserService extends UserDetailsService {

	UserDetail findByEmail(String email);

	boolean addUser(String name, String email, Authorities role);

	boolean changePassword(String user, String oldPassword, String newPassword);

	boolean updateUser(UserDetail admin);

	List<UserDetail> findUsersWithoutPlace();

	UserDetail saveUser(UserDetail selectedUser);

	List<UserDetail> findUsersWithLocal();
}
