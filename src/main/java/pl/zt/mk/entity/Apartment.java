package pl.zt.mk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Michal on 18.04.2016.
 */
@Table
@Getter
@Setter
@ToString
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Block block;

	private Integer apartmentNumber;

	private Integer roomersCount;

	public Apartment(Integer apartmentNumber, Integer roomersCount) {
		this.apartmentNumber = apartmentNumber;
		this.roomersCount = roomersCount;
	}
}
