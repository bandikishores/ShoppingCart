package backingbean;

import java.util.List;

import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.CategoryData;

public class IndexBackingBean extends BaseBackingBean
{
	private List<CategoryData> categoryData; 
	
	public List<CategoryData> getCategoryData() {
		return categoryData;
	}

	public void setCategoryData(List<CategoryData> categoryData) {
		this.categoryData = categoryData;
	}

	public IndexBackingBean() 
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
		
		Util.removeAllBeansExcept("indexBackingBean");
		List categoryList = LoadClasses.getListOfCategories();
		if(Util.isNullList(categoryList))
		{
			setCategoryData(null);
			this.setError("No Categories has been added.");
		}
		else
		{
			setCategoryData(categoryList);
		}
	}

}
