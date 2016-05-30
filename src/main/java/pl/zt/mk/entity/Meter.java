package pl.zt.mk.entity;

import lombok.*;
import org.joda.time.LocalDate;
import pl.zt.mk.lazy.interfaces.IDProvider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zt on 2016-04-11.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Meter implements IDProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private LocalDate date;

	private Double gas;
	private Double hotWater;
	private Double coldWater;
	private Double electricity;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Meter meter = (Meter) o;

		return id != null ? id.equals(meter.id) : meter.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@ManyToOne()

	private Place place;


}
