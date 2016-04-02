package pl.zt.mk.services;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.entity.meta.Authorities;

/**
 * Created by zt on 2016-03-22.
 */
@Service
public interface UserService {

        UserDetail findByEmail(String email);

        Long addUser(String name, String email, Authorities role) throws DataAccessException;

}
