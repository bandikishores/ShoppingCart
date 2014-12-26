package common;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import dao.LoadClasses;
import data.CategoryData;

public class CategoryConverter implements Converter
{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) 
	{
		List<CategoryData> categoryDataList = LoadClasses.getListOfCategories();
		if(!Util.isNullList(categoryDataList))
		{
			for(CategoryData category : categoryDataList)
			{
				if(category.getName().equals(value))
					return category;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) 
	{
		CategoryData categoryObject = (CategoryData) value;
		return categoryObject.getName();
	}
	
}
