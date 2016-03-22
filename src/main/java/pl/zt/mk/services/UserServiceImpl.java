package pl.zt.mk.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.repo.UserRepository;

/**
 * Created by zt on 2016-03-22.
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    private final static Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetail findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Long addUser(String name, String email) {
        String salt = BCrypt.gensalt();
        String password = RandomStringUtils.randomAlphanumeric(10).toLowerCase();
        String hashpw = BCrypt.hashpw(password,salt);
        UserDetail saved =  userRepository.save(new UserDetail(name,email, hashpw));
        log.info(email+":"+password);
        return saved.getId();
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
