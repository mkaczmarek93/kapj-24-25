package pl.zt.mk.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Michal on 10.04.2016.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Block {

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
	private String postCode;

	@OneToMany(mappedBy = "block", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Place> places;

	public Block(String city, String postCode, String street, String flatNumber, List<Place> places) {
		this.city = city;
		this.postCode = postCode;
		this.street = street;
		this.flatNumber = flatNumber;
		this.places = places;
	}

}
