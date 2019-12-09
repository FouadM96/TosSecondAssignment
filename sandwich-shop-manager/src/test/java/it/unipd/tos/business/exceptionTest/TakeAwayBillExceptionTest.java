package it.unipd.tos.business.exceptionTest;

import it.unipd.tos.business.exception.TakeAwayBillException;
import static org.junit.Assert.*;

import org.junit.Test;

public class TakeAwayBillExceptionTest {

	@Test
	public void constructorTest() {
		TakeAwayBillException e= new TakeAwayBillException();
		assertNotNull(e);
	}

}
