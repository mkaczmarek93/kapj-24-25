package pl.zt.mk.converters;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.zt.mk.converters.dto.ReadAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 14.04.2016.
 */
@Slf4j
@Component
public class JsonConverter {

	public ReadAddress getAddress(String json) {
		Gson gson = new Gson();
		List<Results> results = gson.fromJson(json, Results.Container.class).results;
		if (results.isEmpty())
			return new ReadAddress();
		List<AddressComponent> components = results.get(0).addressComponents;
		ReadAddress address = new ReadAddress();
		results.stream().filter(res -> res.types.contains("street_address")).forEach(res -> {
			address.setFormattedAddress(res.formattedAddress);
			boolean isPremise = false;
			for (AddressComponent com : components) {
				if (com.types.contains("route")) {
					address.setStreet(com.longName);
				} else if (com.types.contains("locality")) {
					address.setCity(com.longName);
				} else if (com.types.contains("street_number")) {
					address.setFlatNumber(com.longName);
				} else if (com.types.contains("premise")) {
					address.setFlatNumber(com.longName);
					isPremise = true;
				}
			}
			if (isPremise) {
				address.setStreet(address.getCity());
			}
		});
		return address;
	}

	@ToString
	private class Results {
		@SerializedName("address_components")
		List<AddressComponent> addressComponents;

		@SerializedName("types")
		List<String> types;

		@SerializedName("formatted_address")
		String formattedAddress;

		@ToString
		class Container {
			List<Results> results;
		}
	}

	@ToString
	private class AddressComponent {
		@SerializedName("types")
		List<String> types = new ArrayList<>();
		@SerializedName("short_name")
		String shortName;
		@SerializedName("long_name")
		String longName;
	}

}
