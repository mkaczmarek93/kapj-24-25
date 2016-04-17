package pl.zt.mk.converters.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Created by Michal on 14.04.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReadAddress {

	private String city;

	private String street;

	private String flatNumber;

	private String formattedAddress;

	public boolean isEmpty() {
		return Objects.isNull(city)
				&& Objects.isNull(street)
				&& Objects.isNull(flatNumber)
				&& Objects.isNull(formattedAddress);
	}
}
