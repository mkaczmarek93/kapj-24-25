package pl.zt.mk.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michal on 18.04.2016.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Apartment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	private Block block;

	@Column
	private String apartmentNumber;

	@Column
	private Integer roomersCount;

	public Apartment(String apartmentNumber, Integer roomersCount, Block block) {
		this.apartmentNumber = apartmentNumber;
		this.roomersCount = roomersCount;
		this.block = block;
	}
}
