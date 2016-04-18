package pl.zt.mk.lazy.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.zt.mk.entity.Payment;

/**
 * Created by zt on 2016-04-11.
 */
public class PaymentPageResolver extends AbstractPageResolver<Payment> {

	public PaymentPageResolver(PagingAndSortingRepository<Payment, Long> repository) {
		super(repository);
	}

	@Override
	public Page<Payment> getData(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}

	@Override
	public int getCount() {
		return (int) repository.count();
	}

}
