package pl.zt.mk.lazy.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.repo.MeterRepository;

/**
 * Created by zt on 2016-04-11.
 */
public class MetersPageResolver extends AbstractPageResolver<Meter> {
	private final Place place;
	private final MeterRepository repository;

	public MetersPageResolver(MeterRepository repository, Place userDetail) {
		super(repository);
		this.place = userDetail;
		this.repository = repository;
	}

	@Override
	public Page<Meter> getData(PageRequest pageRequest) {
		return repository.findByPlace(place, pageRequest);
	}

	@Override
	public int getCount() {
		return (int) repository.countByPlace(place);
	}
}
