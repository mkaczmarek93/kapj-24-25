package pl.zt.mk.lazy;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.zt.mk.lazy.interfaces.IDProvider;
import pl.zt.mk.lazy.interfaces.LazyPageResolver;

import java.util.List;
import java.util.Map;

/**
 * Created by zt on 2016-04-06.
 */
public class LazyModel<T extends IDProvider> extends LazyDataModel<T> {

	LazyPageResolver<T> repository;
	Page<T> data;

	public LazyModel(LazyPageResolver<T> repository) {
		this.repository = repository;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		PageRequest pageRequest = getPageRequest(first, pageSize, sortField, sortOrder);


		data = repository.getData(pageRequest);
		this.setRowCount(repository.getCount());

		return data.getContent();
	}


	protected PageRequest getPageRequest(int first, int pageSize, String sortField, SortOrder sortOrder) {
		PageRequest pageRequest;
		if (SortOrder.UNSORTED.equals(sortOrder) || StringUtils.isBlank(sortField)) {
			pageRequest = new PageRequest(first, pageSize);
		} else {
			Sort.Direction sortDir = sortOrder.equals(SortOrder.ASCENDING) ? Sort.Direction.ASC : Sort.Direction.DESC;
			pageRequest = new PageRequest(first / pageSize, pageSize, sortDir, sortField);
		}
		this.setPageSize(pageSize);
		return pageRequest;
	}

	@Override
	public Object getRowKey(T object) {
		return object.getId();
	}

	@Override
	public T getRowData(String rowKey) {
		return repository.findOne(Long.parseLong(rowKey));
	}

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else
			super.setRowIndex(rowIndex % getPageSize());
	}
}
