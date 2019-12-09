////////////////////////////////////////////////////////////////////
// Fouad Mouad 1170480
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;


public class MenuItemImpl implements MenuItem{
	private String name;

	private ItemType itemType;

	private Double price;
	
	public MenuItemImpl(String name, ItemType itemType, double price) {
		super();
		this.name = name;
		this.itemType = itemType;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public ItemType getItemType() {
		return itemType;
	}

	public void setType(ItemType itemType) {
		this.itemType = itemType;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
