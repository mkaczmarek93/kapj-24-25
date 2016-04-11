package pl.zt.mk.lazy.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.zt.mk.entity.Meter;

/**
 * Created by zt on 2016-04-11.
 */
public class MetersPageResolver extends AbstractPageResolver<Meter> {
	public MetersPageResolver(PagingAndSortingRepository<Meter, Long> repository) {
		super(repository);
	}

	@Override
	public Page<Meter> getData(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
}
