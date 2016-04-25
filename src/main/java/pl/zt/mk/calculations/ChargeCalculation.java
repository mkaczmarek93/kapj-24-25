package pl.zt.mk.calculations;

import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.entity.meta.CounterType;

import java.util.*;

/**
 * Created by zt on 2016-04-25.
 */
public class ChargeCalculation {
	private final Integer roomersCount;
	private final Meter prevMonth;
	private final Meter currentMonth;
	private final HashMap<CounterType,List<Payment>> payments;

	public ChargeCalculation(RoomersCounter roomersCounter, Meter prevMonth, Meter currentMonth, List<Payment> currentPayments){
		this.prevMonth = prevMonth;
		this.currentMonth = currentMonth;
		this.roomersCount = roomersCounter.getRoomersCount();
		this.payments = new HashMap();

		currentPayments.forEach(payment -> {
			payments.putIfAbsent(payment.getCounterType(), new ArrayList<>());
			payments.get(payment.getCounterType()).add(payment);
		});
	}

	public Double calculate(){

		double suma = 0D;
		//cold water
		if (payments.get(CounterType.WATER_COLD)!=null)
			suma += (currentMonth.getColdWater() - prevMonth.getColdWater()) * payments.get(CounterType.WATER_COLD).get(0).getPrice();

		if (payments.get(CounterType.WATER_HOT)!=null)
			suma += (currentMonth.getHotWater() - prevMonth.getHotWater()) * payments.get(CounterType.WATER_HOT).get(0).getPrice();

		if (payments.get(CounterType.ELECTRICITY)!=null)
			suma += (currentMonth.getElectricity() - prevMonth.getElectricity()) * payments.get(CounterType.ELECTRICITY).get(0).getPrice();

		if (payments.get(CounterType.GAS)!=null)
			suma += (currentMonth.getGas() - prevMonth.getGas()) * payments.get(CounterType.GAS).get(0).getPrice();

		if (payments.get(CounterType.GARBAGE)!=null){
			suma += roomersCount * payments.get(CounterType.GAS).get(0).getPrice();

		}

		final double[] tempSum = {0};
		if(payments.get(null)!=null){
			payments.get(null).forEach(nullPayment ->{
				tempSum[0] +=nullPayment.getPrice();
			});
		}

		suma+= tempSum[0];


		return suma;

	}
}
