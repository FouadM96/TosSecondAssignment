package it.unipd.tos.modelTest;

import it.unipd.tos.model.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MenuItemTest {

	@Test
	public void MenuItemConstructorTest() {
		
		MenuItem menuItem= new MenuItemImpl("Panino Primavera", ItemType.PANINI, 7);
		assertEquals("Panino Primavera", menuItem.getName());
		assertEquals(ItemType.PANINI, menuItem.getItemType());
		assertEquals(7.0, menuItem.getPrice(), 0.0);
	}

}
