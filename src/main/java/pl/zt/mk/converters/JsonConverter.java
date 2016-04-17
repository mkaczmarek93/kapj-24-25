package pl.zt.mk.converters;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.zt.mk.converters.dto.ReadAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		ReadAddress address = new ReadAddress();
		results.stream().filter(res -> res.types.contains("street_address")).forEach(res -> {
			address.setFormattedAddress(res.formattedAddress);
			boolean isPremise = false;
			for (AddressComponent com : res.addressComponents) {
				if (com.types.contains("route")) {
					address.setStreet(com.longName);
				} else if (com.types.contains("locality")) {
					address.setCity(com.longName);
				} else if (com.types.contains("street_number")) {
					address.setFlatNumber(com.longName);
				} else if (com.types.contains("premise")) {
					address.setFlatNumber(com.longName);
					isPremise = true;
				} else if (com.types.contains("postal_code")) {
					address.setPostCode(com.longName);
				}
			}
			if (isPremise) {
				address.setStreet(address.getCity());
			}
		});
		if (Objects.isNull(address.getPostCode())) {
			results.stream().filter(res -> res.types.contains("postal_code")).forEach(res -> {
				if (!res.types.contains("postal_code_prefix")) {
					res.addressComponents.stream().filter(com -> com.types.contains("postal_code") && !com.types.contains("postal_code_prefix")).forEach(com -> address.setPostCode(com.longName));
				}
			});
		}
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
