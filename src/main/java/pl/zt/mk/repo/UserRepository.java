package pl.zt.mk.repo;

import org.springframework.data.repository.CrudRepository;
import pl.zt.mk.entity.UserDetail;

import java.util.List;

/**
 * Created by zt on 2016-03-22.
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<UserDetail, Long> {

	UserDetail findByEmail(String email);

	UserDetail findByName(String name);

	UserDetail findByEmailAndPassword(String email, String password);

	List<UserDetail> findByPlaceIsNull();
}
