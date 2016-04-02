package pl.zt.mk.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.entity.UserRole;
import pl.zt.mk.entity.meta.Authorities;
import pl.zt.mk.repo.UserRepository;

/**
 * Created by zt on 2016-03-22.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public UserDetail findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Long addUser(String name, String email, Authorities role) throws DataAccessException {
		String password = RandomStringUtils.randomAlphanumeric(10).toLowerCase();
		String hashpw = encoder.encode(password);
		try {
			UserDetail saved = userRepository.save(new UserDetail(name, email, hashpw, new UserRole(role)));
			log.info(email + ":" + password);
			return saved.getId();
		} catch (DataAccessException e) {
			log.debug("user not saved");
			throw e;
		}


	}

	@Override
	public void changePassword(String mail, String oldPassword, String newPassword) {

		UserDetail user = userRepository.findByEmail(mail);
		if (user != null) {
			if (encoder.matches(oldPassword, user.getPassword())) {
				user.setPassword(encoder.encode(newPassword));
				userRepository.save(user);
			}
		}

	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}
}
