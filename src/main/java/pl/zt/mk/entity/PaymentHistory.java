package pl.zt.mk.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.zt.mk.lazy.interfaces.IDProvider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by zt on 2016-04-25.
 */
@Entity
@Getter
@Setter
public class PaymentHistory implements IDProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@ManyToOne
	private Place place;

	@NotNull
	private LocalDate month;

	@NotNull
	private Double charge;

	@NotNull
	Boolean paid;

	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column( unique = true)
	String uuid;

	private LocalDate paymentDate;

}
