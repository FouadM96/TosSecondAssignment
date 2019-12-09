////////////////////////////////////////////////////////////////////
// Fouad Mouad 1170480
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;

public class TakeAwayBillImpl implements TakeAwayBill {

	private static final double commissione= 0.5;
	private static final double sconto= 0.1;
	private static final double commissioneThreshold= 10;
	private static final double scontoThreshold= 50;
	private static final int exceptionThreshold= 30;
	private static final int sandwitchesThreshold= 5;

	public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
		if(itemsOrdered.size() == 0)
		{
			return 0;
		}

		if(itemsOrdered.size() > 30)
		{
			throw new TakeAwayBillException();			
		}

		double totalPrice= calculatePrice(itemsOrdered);

		if(moreThanSandwitchesThresholdOrdered(itemsOrdered)) {
			MenuItem cheapestSandwitch = findCheapestSandwitch(itemsOrdered);
			totalPrice = totalPrice - cheapestSandwitch.getPrice()/2;
		}

		if(priceOfPaniniAndFritti(itemsOrdered) > scontoThreshold) {
			totalPrice = totalPrice - sconto*totalPrice;
		}
		else if(priceOfPaniniAndFritti(itemsOrdered) < commissioneThreshold) {
			totalPrice = totalPrice + commissione;
		}

		return totalPrice;
	}

	private double calculatePrice(List<MenuItem> itemsOrdered) {
		double totalPrice =0;
		for (int i=0; i < itemsOrdered.size(); i+=1) {
			totalPrice += itemsOrdered.get(i).getPrice();
		}
		return totalPrice;
	}

	private double priceOfPaniniAndFritti(List<MenuItem> itemsOrdered) {
		double totalPrice =0;
		for (int i=0; i < itemsOrdered.size(); i+=1) {
			if(itemsOrdered.get(i).getItemType() == ItemType.PANINI
					|| itemsOrdered.get(i).getItemType() == ItemType.FRITTI)
					{
							totalPrice += itemsOrdered.get(i).getPrice();
					}
		}
		return totalPrice;
	}

	private boolean moreThanSandwitchesThresholdOrdered(List<MenuItem> itemsOrdered)
	{
		int sandwitchesFound =0;
		for(int i =0; i<itemsOrdered.size(); i+=1)
		{
			if(itemsOrdered.get(i).getItemType() == ItemType.PANINI)
			{
					sandwitchesFound += 1;
			}
		}
		return (sandwitchesFound > sandwitchesThreshold);
	}

	private MenuItem findCheapestSandwitch(List<MenuItem> itemsOrdered)
	{
		MenuItem cheapestSandwitch = null;
		for(int i =0; i<itemsOrdered.size(); i+=1)
		{
			if(itemsOrdered.get(i).getItemType() == ItemType.PANINI)
			{
				if(cheapestSandwitch == null ||
						itemsOrdered.get(i).getPrice() <
									cheapestSandwitch.getPrice())
									{
										cheapestSandwitch = itemsOrdered.get(i);
									}
			}
		}
		return cheapestSandwitch;
	}

	public static double getCommissione() {
		return commissione;
	}

	public static double getSconto() {
		return sconto;
	}

	public static double getCommissioneThreshold() {
		return commissioneThreshold;
	}

	public static double getScontoThreshold() {
		return scontoThreshold;
	}

	public static int getExceptionThreshold() {
		return exceptionThreshold;
	}

	public static int getSandwitchesThreshold() {
		return sandwitchesThreshold;
	}

}
