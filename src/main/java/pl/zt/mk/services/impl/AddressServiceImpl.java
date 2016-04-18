package pl.zt.mk.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.Block;
import pl.zt.mk.repo.AddressRepository;
import pl.zt.mk.services.AddressService;

import java.util.List;

/**
 * Created by Michal on 10.04.2016.
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;


	@Override
	public boolean addAddress(Block block) {
		try {
			Block saved = addressRepository.save(block);
			log.info("Saved block: " + saved.toString());
			return true;
		} catch (DataAccessException e) {
			log.debug("Block not saved.");
			return false;
		}
	}

	@Override
	public List<String> getCities() {
		return null;
	}

	@Override
	public List<String> getStreets() {
		return null;
	}
}
