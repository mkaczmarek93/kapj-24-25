package pl.zt.mk.services;

import pl.zt.mk.entity.Place;

/**
 * Created by zt on 2016-04-12.
 */
public interface PlaceService {

	Place findPlaceByUserEmail(String email);
}
