package pl.zt.mk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDate;
import pl.zt.mk.lazy.interfaces.IDProvider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by zt on 2016-04-25.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class PaymentHistory implements IDProvider {

	@NotNull
	Boolean paid;
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(unique = true)
	String uuid;
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
	private LocalDate paymentDate;

	public PaymentHistory() {
		super();
	}

	public PaymentHistory(Place place, org.joda.time.LocalDate localDate, Double sum) {
		this.place = place;
		this.month = localDate;
		this.charge = sum;
		this.paid = Boolean.FALSE;

	}

	@PrePersist
	public void initializeUUID() {
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();
		}
	}
}
