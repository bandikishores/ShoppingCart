package data;

import common.Constants;

public class OrderData extends BaseData 
{
	private Long id;
	private Long qty= new Long(0);
	private String username = Constants.EMPTY_STRING;
	private ItemData item = new ItemData();
	private Double cost = new Double(-1);
	private String status = Constants.PROCESSING;
	private String name = Constants.EMPTY_STRING;
	private String description = Constants.EMPTY_STRING;
	private String address = Constants.EMPTY_STRING;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public ItemData getItem() {
		return item;
	}
	public void setItem(ItemData item) {
		this.item = item;
	}

}
