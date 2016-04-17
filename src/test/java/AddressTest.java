import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.json.JSONObject;
import org.primefaces.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.ProjectStarter;
import pl.zt.mk.converters.JsonConverter;
import pl.zt.mk.converters.dto.ReadAddress;
import pl.zt.mk.entity.Address;
import pl.zt.mk.repo.AddressRepository;

import java.net.URL;

/**
 * Created by Michal on 11.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProjectStarter.class})
@DirtiesContext
public class AddressTest extends TestCase {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private JsonConverter jsonConverter;

	@Test
	public void addingAddressWithApartmentTest() {
		Address saved = null;
		try {
			String city = "Lodz";
			String postCode = "90-700";
			String street = "Piotrkowska";
			String flatNumber = "13";
			Integer apartmentNumber = 43;
			Integer collaborators = 3;
			Address address = new Address(city, postCode, street, flatNumber, apartmentNumber, collaborators);
			saved = addressRepository.save(address);
			assertEquals(address, saved);
		} finally {
			if (null != saved)
				addressRepository.delete(saved);
		}
	}

	@Test
	public void jsonConverterTest() throws Exception {
		String json = readUrl(51.743894, 19.444409);
		ReadAddress address = jsonConverter.getAddress(json);
		assertEquals("Łódź", address.getCity());
		assertEquals("Wróblewskiego", address.getStreet());
		assertEquals("21", address.getFlatNumber());
		assertEquals("93-578", address.getPostCode());
	}

	private String readUrl(double lat, double lng) throws Exception {
		final String GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json?language=pl";
		final String GEOCODING_RESULT_TYPE = "&result_type=street_address|postal_code";
		final String GOOGLE_API_KEY = "&key=AIzaSyBdC2NNDL1PYq1O131l7KDRylzYa5dx1D4";
		URL url = new URL(GEOCODING_URL + GEOCODING_RESULT_TYPE + "&latlng=" + lat + "," + lng + GOOGLE_API_KEY);
		JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(url)).nextValue();
		return jo.toString();
	}

}
