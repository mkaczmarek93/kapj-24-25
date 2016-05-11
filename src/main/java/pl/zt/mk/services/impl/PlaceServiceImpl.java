package pl.zt.mk.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.Place;
import pl.zt.mk.repo.PlaceRepository;
import pl.zt.mk.services.PlaceService;

import java.util.List;

/**
 * Created by zt on 2016-04-12.
 */
@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceRepository placeRepository;

	@Override
	public Place findPlaceByUserEmail(String email) {
		return placeRepository.findByUserEmail(email);
	}

	@Override
	public List<Place> findPlacesWithoutUser() {
		return placeRepository.findByUserDetailIsNull();
	}

	@Override
	public List<Place> findAll() {
		return IteratorUtils.toList(placeRepository.findAll().iterator());
	}

	@Override
	public List<Place> findPlacesConnectedWithAnyUser() {
		return placeRepository.findByUserDetailIsNotNull();
	}

}
