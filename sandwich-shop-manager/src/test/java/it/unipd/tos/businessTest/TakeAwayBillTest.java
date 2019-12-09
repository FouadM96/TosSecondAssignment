package it.unipd.tos.businessTest;

import it.unipd.tos.business.*;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TakeAwayBillTest {

	private TakeAwayBill takeAwayBill= new TakeAwayBillImpl();
	@Test
	public void testgetOrderPrice_EmptyBill()  throws TakeAwayBillException{
		MenuItem [] itemsOrdered= {};
		List<MenuItem> itemsOrderedList = createitemsOrderedList(itemsOrdered);
		Double actual = takeAwayBill.getOrderPrice(itemsOrderedList);
		Double expected = 0.0;
		assertTotalPrice(expected, actual);
	}

	@Test(expected = TakeAwayBillException.class)
	public void testgetOrderPrice_ThrowsException()  throws TakeAwayBillException{
		MenuItem [] itemsOrdered= new MenuItem[TakeAwayBillImpl.getExceptionThreshold()+2];
		constructInputArray(itemsOrdered, 11);
		List<MenuItem> itemsOrderedList = createitemsOrderedList(itemsOrdered);
		@SuppressWarnings("unused")
		Double actual = takeAwayBill.getOrderPrice(itemsOrderedList);
	}

	@Test
	public void testgetOrderPrice_NoDiscount() throws TakeAwayBillException {
		MenuItem [] itemsOrdered= new MenuItem[TakeAwayBillImpl.getSandwitchesThreshold()];
		constructInputArray(itemsOrdered, 7);
		List<MenuItem> itemsOrderedList = createitemsOrderedList(itemsOrdered);
		Double actual = takeAwayBill.getOrderPrice(itemsOrderedList);
		assertTotalPrice(35.0, actual);
	}

	@Test
	public void testgetOrderPrice_DiscountOnlyOnASandwitch() throws TakeAwayBillException {
		MenuItem [] itemsOrdered= new MenuItem[TakeAwayBillImpl.getSandwitchesThreshold()+1];
		constructInputArray(itemsOrdered, 7);
		List<MenuItem> itemsOrderedList = createitemsOrderedList(itemsOrdered);
		Double actual = takeAwayBill.getOrderPrice(itemsOrderedList);
		assertTotalPrice(38.5, actual);
	}

	@Test
	public void testgetOrderPrice_DiscountAppliedToFinalBill() throws TakeAwayBillException {
		MenuItem [] itemsOrdered= new MenuItem[5];
		double singleItemPrice= TakeAwayBillImpl.getScontoThreshold()/5 + 1.0;
		constructInputArray(itemsOrdered, singleItemPrice);
		itemsOrdered[0]= new MenuItemImpl("OliveAscolane", ItemType.FRITTI, singleItemPrice);
		List<MenuItem> itemsOrderedList = createitemsOrderedList(itemsOrdered);
		Double actual = takeAwayBill.getOrderPrice(itemsOrderedList);
		Double expected = singleItemPrice*itemsOrdered.length-
								TakeAwayBillImpl.getSconto()*singleItemPrice*itemsOrdered.length;
		assertTotalPrice(expected, actual);
	}

	@Test
	public void testgetOrderPrice_commissioneThresholdApplied() throws TakeAwayBillException {
		MenuItem [] itemsOrdered = {new MenuItemImpl("Panino Primavera", ItemType.PANINI,TakeAwayBillImpl.getCommissioneThreshold()-3)};
		List<MenuItem> itemsOrderedList = createitemsOrderedList(itemsOrdered);
		Double expected= itemsOrdered[0].getPrice() +
								TakeAwayBillImpl.getCommissione();
		Double actual = takeAwayBill.getOrderPrice(itemsOrderedList);
		assertTotalPrice(expected, actual);
	}

	private void assertTotalPrice(Double expected, Double actual) {
		assertEquals(expected, actual);
	}

	private void constructInputArray(MenuItem[] itemsOrdered, double price) {
		for(int i=0; i<itemsOrdered.length; i++) {
			itemsOrdered[i]= new MenuItemImpl("Panino Primavera", ItemType.PANINI, price);
		}
	}

	private List<MenuItem> createitemsOrderedList(MenuItem[] itemsOrdered) {
		List<MenuItem> itemsOrderedList = new ArrayList<MenuItem>();
		for (int i=0; i<itemsOrdered.length; i+=1) {
			itemsOrderedList.add(itemsOrdered[i]);
		}
		return itemsOrderedList;
	}



}
