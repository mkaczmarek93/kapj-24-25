package pl.zt.mk.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;
import pl.zt.mk.entity.meta.CounterType;
import pl.zt.mk.lazy.interfaces.IDProvider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zt on 2016-04-05.
 * <p>
 * Opłaty- zasada działania
 * Każda z opłat ma swoją datę rozpoczęcia, jeśli opłata nie ma daty zakończenia,
 * oznacza to że opłata ta zawsze jest pobierana.
 * Edycja opłaty to de facto ustawienie daty zakończenia na poprzedniej opłacie
 * oraz ustawienie daty rozpoczęcia nowego obiektu;
 * <p>
 * Wyliczanie opłat dla mieszkania to pobranie rodzajów opłat aktualnych zgodnie z ustanowionym dniem przeliczania opłat.
 * <p>
 * Opłata może posiadać cene za jednostkę, bądź stałą opłate miesięczną.
 * <p>
 * <p>
 *
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Payment implements IDProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;
	private String description;
	@Enumerated
	private CounterType counterType;
	private String unit;
	@NotNull
	private Double price;
	@NotNull
	private LocalDate startDate;
	private LocalDate endDate;

	private boolean active;
}
