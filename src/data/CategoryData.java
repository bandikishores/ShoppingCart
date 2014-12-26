package data;

import java.util.HashSet;
import java.util.Set;

import common.Constants;

public class CategoryData extends BaseData 
{
	private Long id;
	private String name = Constants.EMPTY_STRING;
	private String description = Constants.EMPTY_STRING;
	private Set<ItemData> items = new HashSet<ItemData>(0);
	
	public Set<ItemData> getItems() {
		return items;
	}
	public void setItems(Set<ItemData> items) {
		this.items = items;
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

}
