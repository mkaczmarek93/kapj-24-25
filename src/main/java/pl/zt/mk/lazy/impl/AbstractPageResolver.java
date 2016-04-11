package pl.zt.mk.lazy.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.zt.mk.lazy.interfaces.LazyPageResolver;

/**
 * Created by zt on 2016-04-11.
 */
public abstract class AbstractPageResolver<T> implements LazyPageResolver<T> {
	protected final PagingAndSortingRepository<T, Long> repository;
	protected int count;

	public AbstractPageResolver(PagingAndSortingRepository<T, Long> repository) {
		this.repository = repository;
	}

	@Override
	public T findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public abstract Page<T> getData(PageRequest pageRequest);

	@Override
	public int getCount() {
		return (int) repository.count();
	}
}
