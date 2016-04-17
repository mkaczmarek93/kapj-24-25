import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.ProjectStarter;
import pl.zt.mk.entity.Address;
import pl.zt.mk.repo.AddressRepository;

/**
 * Created by Michal on 11.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProjectStarter.class})
@DirtiesContext
public class AddressTest extends TestCase {

	@Autowired
	AddressRepository addressRepository;

	@Test
	public void addingAddressWithApartmentTest() {
		Address saved = null;
		try {
			String city = "Lodz";
			String street = "Piotrkowska";
			String flatNumber = "13";
			Integer apartmentNumber = 43;
			Integer collaborators = 3;
			Address address = new Address(city, street, flatNumber, apartmentNumber, collaborators);
			saved = addressRepository.save(address);
			assertEquals(saved.getCity(), city);
			assertEquals(saved.getStreet(), street);
			assertEquals(saved.getFlatNumber(), flatNumber);
			assertEquals(saved.getApartmentNumber(), apartmentNumber);
			assertEquals(saved.getCollaborators(), collaborators);
		} finally {
			if (null != saved)
				addressRepository.delete(saved);
		}
	}

	@Test
	public void addingAddressWithoutApartmentTest() {
		Address saved = null;
		try {
			String city = "Lodz";
			String street = "Piotrkowska";
			String flatNumber = "13";
			Integer collaborators = 3;
			Address address = new Address(city, street, flatNumber, null, collaborators);
			saved = addressRepository.save(address);
			assertEquals(saved.getCity(), city);
			assertEquals(saved.getStreet(), street);
			assertEquals(saved.getFlatNumber(), flatNumber);
			assertNull(saved.getApartmentNumber());
			assertEquals(saved.getCollaborators(), collaborators);
		} finally {
			if (null != saved)
				addressRepository.delete(saved);
		}
	}

}
