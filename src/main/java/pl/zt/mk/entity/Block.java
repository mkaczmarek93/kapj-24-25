package pl.zt.mk.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Michal on 10.04.2016.
 */
@Table
@Entity
@Getter
@Setter
@ToString
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

	@OneToMany(mappedBy = "block")
	private List<Apartment> apartments;

	public Block(String city, String postCode, String street, String flatNumber) {
		this.city = city;
		this.postCode = postCode;
		this.street = street;
		this.flatNumber = flatNumber;
	}

}
