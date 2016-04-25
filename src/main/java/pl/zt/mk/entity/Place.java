package pl.zt.mk.entity;

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

}
