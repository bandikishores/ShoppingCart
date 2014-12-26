package data;

import common.Constants;

public class ItemData extends BaseData 
{
	private Long id;
	private String name = Constants.EMPTY_STRING;
	private String description = Constants.EMPTY_STRING;
	private String imageUrl = Constants.EMPTY_STRING; 
	private CategoryData category = new CategoryData();
	private Long categoryId = new Long(-1);
	private Double cost = new Double(-1);
	private Long qty= new Long(-1);
	private String username = Constants.EMPTY_STRING;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public CategoryData getCategory() {
		return category;
	}
	public void setCategory(CategoryData category) {
		this.category = category;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
