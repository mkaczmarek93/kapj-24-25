import junit.framework.TestCase;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zt.mk.ProjectStarter;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.entity.meta.CounterType;
import pl.zt.mk.repo.PaymentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Map<CounterType,List<Payment>> paymentsMap = new HashMap();

		paymentList.forEach(payment -> {
			paymentsMap.putIfAbsent(payment.getCounterType(), new ArrayList<>());
			paymentsMap.get(payment.getCounterType()).add(payment);
		});


		double suma = 0D;
		//cold water
		if (paymentsMap.get(CounterType.WATER_COLD)!=null)
			suma += (actualMonth.getColdWater() - prevMonth.getColdWater()) * paymentsMap.get(CounterType.WATER_COLD).get(0).getPrice();

		if (paymentsMap.get(CounterType.WATER_HOT)!=null)
			suma += (actualMonth.getHotWater() - prevMonth.getHotWater()) * paymentsMap.get(CounterType.WATER_HOT).get(0).getPrice();

		if (paymentsMap.get(CounterType.ELECTRICITY)!=null)
			suma += (actualMonth.getElectricity() - prevMonth.getElectricity()) * paymentsMap.get(CounterType.ELECTRICITY).get(0).getPrice();

		if (paymentsMap.get(CounterType.GAS)!=null)
			suma += (actualMonth.getGas() - prevMonth.getGas()) * paymentsMap.get(CounterType.GAS).get(0).getPrice();

		final double[] tempSum = {0};
		if(paymentsMap.get(null)!=null){

			paymentsMap.get(null).forEach(nullPayment ->{
				tempSum[0] +=nullPayment.getPrice();
			});
		}

		suma+= tempSum[0];


		assertTrue(suma == 310D);
	}
}
