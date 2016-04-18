package pl.zt.mk.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.Block;

/**
 * Created by Michal on 10.04.2016.
 */
@Repository
public interface AddressRepository extends CrudRepository<Block, Long> {
}
