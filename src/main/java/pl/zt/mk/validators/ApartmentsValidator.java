package pl.zt.mk.validators;

import org.springframework.stereotype.Component;
import pl.zt.mk.annotations.RequestScoped;
import pl.zt.mk.entity.Place;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Michal on 21.04.2016.
 */
@Component
@RequestScoped
public class ApartmentsValidator {
	public boolean validateApartmentsNumberInOneBlock(List<Place> places) {
		Set<String> numbers = places.stream().map(Place::getApartmentNumber).collect(Collectors.toSet());
		return numbers.size() == places.size();
	}
}
