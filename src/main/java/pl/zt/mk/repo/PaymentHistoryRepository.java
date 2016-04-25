package pl.zt.mk.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;

/**
 * Created by zt on 2016-04-25.
 */
@Repository
public interface PaymentHistoryRepository extends PagingAndSortingRepository<PaymentHistory,Long>{

	Page<PaymentHistory>  findByPlace(Place place, Pageable pageable);

	int countByPlace(Place place);
}
