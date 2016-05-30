package pl.zt.mk.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PaymentHistory that = (PaymentHistory) o;

		if (paid != null ? !paid.equals(that.paid) : that.paid != null) return false;
		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (place != null ? !place.equals(that.place) : that.place != null) return false;
		if (month != null ? !month.equals(that.month) : that.month != null) return false;
		if (charge != null ? !charge.equals(that.charge) : that.charge != null) return false;
		return paymentDate != null ? paymentDate.equals(that.paymentDate) : that.paymentDate == null;

	}

	@Override
	public int hashCode() {
		int result = paid != null ? paid.hashCode() : 0;
		result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (place != null ? place.hashCode() : 0);
		result = 31 * result + (month != null ? month.hashCode() : 0);
		result = 31 * result + (charge != null ? charge.hashCode() : 0);
		result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
		return result;
	}
}
