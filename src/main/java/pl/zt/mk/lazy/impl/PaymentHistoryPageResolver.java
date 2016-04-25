package pl.zt.mk.lazy.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.lazy.interfaces.LazyPageResolver;
import pl.zt.mk.repo.PaymentHistoryRepository;

/**
 * Created by zt on 2016-04-25.
 */
public class PaymentHistoryPageResolver extends AbstractPageResolver<PaymentHistory> {

	private final PaymentHistoryRepository repository;
	private final Place place;

	public PaymentHistoryPageResolver(PaymentHistoryRepository repository, Place place) {
		super(repository);
		this.repository = repository;
		this.place = place;
	}

	@Override
	public Page<PaymentHistory> getData(PageRequest pageRequest) {
		return repository.findByPlace(place,pageRequest);
	}

	@Override
	public int getCount() {
		return repository.countByPlace(place);
	}
}
