package backingbean;

import java.util.List;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.ItemData;

public class ItemListBackingBean extends BaseBackingBean 
{
	private List<ItemData> itemData; 
	
	public List<ItemData> getItemData() {
		return itemData;
	}

	public void setItemData(List<ItemData> itemData) {
		this.itemData = itemData;
	}

	public ItemListBackingBean() 
	{
		super();
		setForwardMessageWasSet(false);
		if(isForwardMessageSet())
		{
			setForwardMessageSet(false);
			setForwardMessageWasSet(true);
		}
		else
			this.clearError();
		
		Util.removeAllBeansExcept("itemListBackingBean");
		
		Long categoryId = -1L;
		
		if((Connection.getRequest().getParameter("categoryId") != null 
				&& Long.parseLong(Connection.getRequest().getParameter("categoryId").toString()) >= 0))
		{
			categoryId = Long.parseLong(Connection.getRequest().getParameter("categoryId").toString());
		}
		
		if(categoryId < 0L && (Connection.getRequest().getAttribute("categoryId") != null 
				&& Long.parseLong(Connection.getRequest().getAttribute("categoryId").toString()) >= 0))
		{
			categoryId = Long.parseLong(Connection.getRequest().getAttribute("categoryId").toString());
		}
		
		if(categoryId < 0L && (Connection.getRequest().getSession().getAttribute("categoryId") != null 
				&& Long.parseLong(Connection.getRequest().getSession().getAttribute("categoryId").toString()) >= 0))
		{
			categoryId = Long.parseLong(Connection.getRequest().getSession().getAttribute("categoryId").toString());
		}
		
		String searchText = Constants.EMPTY_STRING;
		if(categoryId < 0L && Util.isNullTrimmedString(searchText) && (Connection.getRequest().getParameter("searchText") != null 
				&& !Util.isNullTrimmedString(Connection.getRequest().getParameter("searchText").toString())))
		{
			searchText = Connection.getRequest().getParameter("searchText").toString();
		}

		if(categoryId < 0L && Util.isNullTrimmedString(searchText) && (Connection.getRequest().getAttribute("searchText") != null 
				&& !Util.isNullTrimmedString(Connection.getRequest().getAttribute("searchText").toString())))
		{
			searchText = Connection.getRequest().getAttribute("searchText").toString();
		}

		if(categoryId < 0L && Util.isNullTrimmedString(searchText) && (Connection.getRequest().getSession().getAttribute("searchText") != null 
				&& !Util.isNullTrimmedString(Connection.getRequest().getSession().getAttribute("searchText").toString())))
		{
			searchText = Connection.getRequest().getSession().getAttribute("searchText").toString();
		}
		
		if(categoryId >= 0)
		{
			List itemList = LoadClasses.getListOfItemsForCategory(categoryId);
			if(Util.isNullList(itemList))
			{
				setItemData(null);
				this.setError("No Items have been added.");
			}
			else
			{
				setItemData(itemList);
			}
		}
		else if(!Util.isNullTrimmedString(searchText))
		{
			List itemList = LoadClasses.getListOfItemForSearchText(searchText);
			Util.removeAllBeansExcept(Constants.EMPTY_STRING);
			if(Util.isNullList(itemList))
			{
				setItemData(null);
				this.setError("No Items have been found for search text : " + searchText);
			}
			else
			{
				setItemData(itemList);
			}
		}
		else
		{
			setItemData(null);
			this.setError("Invalid Access!! No Category has been selected.");
		}
	}


}
