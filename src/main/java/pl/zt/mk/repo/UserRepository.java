package pl.zt.mk.repo;

import org.springframework.data.repository.CrudRepository;
import pl.zt.mk.entity.UserDetail;

/**
 * Created by zt on 2016-03-22.
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<UserDetail,Long> {

    UserDetail findByEmail(String email);

    UserDetail findByName(String name);
}
