package pl.zt.mk.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;
import pl.zt.mk.entity.meta.PaymentType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
 * TODO : ustalić daty przeliczania opłat
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private PaymentType type;
	private String unit;
	private Double price;
	private LocalDate startDate;
	private LocalDate endDate;
}
