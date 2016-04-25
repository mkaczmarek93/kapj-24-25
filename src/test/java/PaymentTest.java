import junit.framework.TestCase;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.ProjectStarter;
import pl.zt.mk.calculations.ChargeCalculation;
import pl.zt.mk.calculations.RoomersCounter;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.repo.PaymentRepository;

import java.util.List;

/**
 * Created by zt on 2016-04-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectStarter.class)
@TestPropertySource(locations = "classpath:application.properties")
public class PaymentTest extends TestCase{

	@Autowired
	PaymentRepository repository;



	@Test
	public void calculationAlogorithmTest(){
		//wszystkie opłaty w bazie nie mają ustawionej daty zakończenia
		//oczekiwana wartość : lista 7 elementów
		List<Payment> paymentList = repository.findPaymentsForDate(new LocalDate(),new LocalDate());
		assertNotNull(paymentList);
		assertTrue(paymentList.size() == 7);

		//mock stanu liczników poprzedniego miesiąca oraz aktualnego miesiąca
		Meter prevMonth = new Meter(1L,new LocalDate(),10d,10d,10d,10d,null);
		Meter actualMonth = new Meter(2l, new LocalDate(),20d,20d,20d,20d,null);

		RoomersCounter roomersCounter = Mockito.mock(RoomersCounter.class);
		Mockito.when(roomersCounter.getRoomersCount()).thenReturn(5);
		ChargeCalculation calculation = new ChargeCalculation(roomersCounter,prevMonth,actualMonth,paymentList);
		double suma = 0D;
		suma+= calculation.calculate();

		assertTrue(suma == 310D);
	}
}
