package pl.zt.mk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.repo.UserRepository;

/**
 * Created by zt on 2016-03-22.
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetail findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
