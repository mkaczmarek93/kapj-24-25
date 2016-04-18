package pl.zt.mk.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.Place;

/**
 * Created by zt on 2016-04-12.
 */
@Repository
public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {

	@Query("select distinct l from Place l where l.userDetail.email =:email")
	Place findByUserEmail(@Param("email") String email);
}
