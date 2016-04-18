package pl.zt.mk.services;

import pl.zt.mk.entity.Block;

import java.util.List;

/**
 * Created by Michal on 10.04.2016.
 */
public interface AddressService {
	boolean addAddress(Block block);

	List<String> getCities();

	List<String> getStreets();
}
