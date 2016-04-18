package pl.zt.mk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zt on 2016-04-11.
 */
@Entity
@Getter
@Setter
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//address

	//

	@OneToOne(mappedBy = "place")
	private UserDetail userDetail;

	@OneToMany(mappedBy = "place")
	private List<Meter> meters;
}
