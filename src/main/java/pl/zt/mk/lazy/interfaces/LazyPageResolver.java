package pl.zt.mk.lazy.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by zt on 2016-04-11.
 */
public interface LazyPageResolver<T> {
	T findOne(Long id);

	Page<T> getData(PageRequest pageRequest);

	int getCount();
}
