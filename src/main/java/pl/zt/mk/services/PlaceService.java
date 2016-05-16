package pl.zt.mk.services;

import pl.zt.mk.entity.Place;

import java.util.List;

/**
 * Created by zt on 2016-04-12.
 */
public interface PlaceService {

	Place findPlaceByUserEmail(String email);

	List<Place> findPlacesWithoutUser();

	List<Place> findAll();

	List<Place> findPlacesConnectedWithAnyUser();

}
