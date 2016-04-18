package pl.zt.mk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.Place;
import pl.zt.mk.repo.PlaceRepository;
import pl.zt.mk.services.PlaceService;

/**
 * Created by zt on 2016-04-12.
 */
@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceRepository placeRepository;

	@Override
	public Place findPlaceByUserEmail(String email) {
		return placeRepository.findByUserEmail(email);
	}
}
