package pl.zt.mk.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.zt.mk.calculations.RoomersCounter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zt on 2016-04-11.
 */
@Entity
@Getter
@Setter
public class Place  implements RoomersCounter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	private Block block;

	@Column
	private String apartmentNumber;

	@Column
	private Integer roomersCount;

	@OneToOne(mappedBy = "place")
	private UserDetail userDetail;

	@OneToMany(mappedBy = "place")
	private List<Meter> meters;

	@OneToMany(mappedBy = "place")
	List<PaymentHistory> paymentHistories;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Place place = (Place) o;

		if (id != null ? !id.equals(place.id) : place.id != null) return false;
		if (block != null ? !block.equals(place.block) : place.block != null) return false;
		if (apartmentNumber != null ? !apartmentNumber.equals(place.apartmentNumber) : place.apartmentNumber != null)
			return false;
		return roomersCount != null ? roomersCount.equals(place.roomersCount) : place.roomersCount == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (block != null ? block.hashCode() : 0);
		result = 31 * result + (apartmentNumber != null ? apartmentNumber.hashCode() : 0);
		result = 31 * result + (roomersCount != null ? roomersCount.hashCode() : 0);
		return result;
	}
}
