package pl.zt.mk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

	@ManyToOne()
	private Place place;
}
