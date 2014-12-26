package data;

import common.Constants;

public class CartData extends BaseData 
{
	private Long id;
	private Long itemId = new Long(-1);
	private Long qty= new Long(0);
	private String username = Constants.EMPTY_STRING;
	private ItemData item = new ItemData();
	
	public ItemData getItem() {
		return item;
	}
	public void setItem(ItemData item) {
		this.item = item;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
