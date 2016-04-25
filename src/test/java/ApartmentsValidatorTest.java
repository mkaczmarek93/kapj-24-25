import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.zt.mk.entity.Place;
import pl.zt.mk.validators.ApartmentsValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 25.04.2016.
 */
public class ApartmentsValidatorTest {

	private ApartmentsValidator validator;

	@Before
	public void init() {
		validator = new ApartmentsValidator();
	}

	@Test
	public void validateCorrectApartmentNumber() {
		List<Place> places = new ArrayList<>();
		places.add(getPlace("1"));
		places.add(getPlace("2"));
		places.add(getPlace("1A"));
		Assert.assertTrue(validator.validateApartmentsNumberInOneBlock(places));
	}

	@Test
	public void validateIncorrectApartmentNumber() {
		List<Place> places = new ArrayList<>();
		places.add(getPlace("1"));
		places.add(getPlace("2"));
		places.add(getPlace("1"));
		Assert.assertFalse(validator.validateApartmentsNumberInOneBlock(places));
	}

	private Place getPlace(String apartmentNumber) {
		Place p = new Place();
		p.setApartmentNumber(apartmentNumber);
		return p;
	}
}
