/**
 * 
 */
package com.capgemini.khaana.dtos;

/**
 * 
 */

public class ItemDTO {

	private Long itemID;

	private Integer quantity;

	public ItemDTO(Long itemID, Integer quantity) {
		super();
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public ItemDTO() {
		super();
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemDTO [itemID=" + itemID + ", quantity=" + quantity + "]";
	}

}
