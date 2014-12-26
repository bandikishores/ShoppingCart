package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.Connection;
import common.Util;
import data.CartData;
import data.ItemData;
import data.OrderData;

public class LoadClasses 
{
	public static List getListOfCategories()
	{
		Session session = Connection.getCurrentDBSession();
		return session.createQuery("From CategoryData catergory").list();		
	}
	
	public static List loadClassesByHQL(String HQL)
	{
		Session session = Connection.getCurrentDBSession();
		return session.createQuery(HQL).list();		
	}
	
	public static Query returnQueryByHQL(String HQL)
	{
		Session session = Connection.getCurrentDBSession();
		return session.createQuery(HQL);		
	}
	
	public static List getListOfItemsForCategory(long catId)
	{
		Session session = Connection.getCurrentDBSession();
		Query query = returnQueryByHQL("From ItemData item where item.categoryId = :categoryId");
		query.setParameter("categoryId", catId);
		return query.list();
	}

	public static List<ItemData> getListOfItems() 
	{
		Session session = Connection.getCurrentDBSession();
		return session.createQuery("From ItemData item").list();
	}

	public static List<CartData> getListOfItemsInCartForUser(String username) 
	{
		Session session = Connection.getCurrentDBSession();
		Query query = returnQueryByHQL("From CartData cart where cart.username = :username");
		query.setParameter("username", username);
		return query.list();
	}
	
	public static ItemData getItembyItemId(Long itemId) 
	{
		Session session = Connection.getCurrentDBSession();
		Query query = returnQueryByHQL("From ItemData item where item.id = :itemId");
		query.setParameter("itemId", itemId);
		List list = query.list();
		if(Util.isNullList(list))
			return null;
		else
			return (ItemData) list.get(0);
	}

	public static List getListOfItemForSearchText(String searchText) 
	{
		Criteria crit = Connection.getCurrentDBSession().createCriteria(ItemData.class);
		crit.add(Restrictions.or(Restrictions.ilike("name", "%"+searchText+"%"), Restrictions.ilike("description", "%"+searchText+"%"))); // ignore case, like condition
		List<ItemData> list = crit.list();
		return list;
	}

	public static List<OrderData> getListOfOrdersForUser(String username) 
	{
		Session session = Connection.getCurrentDBSession();
		Query query = returnQueryByHQL("From OrderData order where order.username = :username");
		query.setParameter("username", username);
		return query.list();
	}

}
