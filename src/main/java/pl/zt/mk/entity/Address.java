package pl.zt.mk.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Michal on 10.04.2016.
 */
@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String city;

	@Column
	private String street;

	@Column
	private String flatNumber;

	@Column
	private Integer apartmentNumber;

	@Column
	private Integer collaborators;

	public Address(String city, String street, String flatNumber, Integer apartmentNumber, Integer collaborators) {
		this.city = city;
		this.street = street;
		this.flatNumber = flatNumber;
		this.apartmentNumber = apartmentNumber;
		this.collaborators = collaborators;
	}

}
