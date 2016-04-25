import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.primefaces.json.JSONObject;
import org.primefaces.json.JSONTokener;
import pl.zt.mk.converters.JsonConverter;
import pl.zt.mk.converters.dto.ReadAddress;

import java.net.URL;

/**
 * Created by Michal on 11.04.2016.
 */
public class BlockTest {

	private JsonConverter jsonConverter;

	@Test
	public void jsonConverterTest() throws Exception {
		jsonConverter = new JsonConverter();
		String json = readUrl(51.743894, 19.444409);
		ReadAddress address = jsonConverter.getAddress(json);
		Assert.assertEquals("Łódź", address.getCity());
		Assert.assertEquals("Wróblewskiego", address.getStreet());
		Assert.assertEquals("21", address.getFlatNumber());
		Assert.assertEquals("93-578", address.getPostCode());
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
