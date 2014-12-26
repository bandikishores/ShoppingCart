package backingbean;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.TestData;

public class TestBackingBean extends BaseBackingBean 
{
	TestData stateData = new TestData();
	String nameSelected = Constants.EMPTY_STRING;
	
	public String getNameSelected() {
		return nameSelected;
	}

	public void setNameSelected(String nameSelected) {
		this.nameSelected = nameSelected;
	}

	public String validateLogin()
	{
		this.clearError();
		Session session = Connection.getCurrentDBSession();
		TestData loadedData = null;
		/*try
		{
			loadedData = (TestData) session.load(TestData.class, stateData.getId());
		}
		catch(ObjectNotFoundException ex)
		{
			ex.printStackTrace();
			this.setError("Invalid Values");
			return Constants.ERROR;
		}*/
		
		Query query = LoadClasses.returnQueryByHQL(" From TestData test where test.name = :testName ");
		query.setParameter("testName", this.nameSelected);
		List<TestData> list = query.list();
		
		if(Util.isNullList(list))
		{
			System.out.println("loaded object not found");
			
			boolean trySql = false;
			
			if(trySql)
			{
				String sqlQuery = "insert into test values (:nameSelected, :nameSelected, TEST_ID_SEQ.nextval)";
				SQLQuery queryObj = Connection.getCurrentDBSession().createSQLQuery(sqlQuery);
				queryObj.setParameter("nameSelected", this.getNameSelected());
				System.out.println("number of rows updated " + queryObj.executeUpdate() );
				System.out.println("inserted through SQL");
			}
			else
			{
				TestData test = new TestData();
				test.setName(getNameSelected());
				test.setName(getNameSelected());
				System.out.println("inserted through HQL");
				
				Connection.getCurrentDBSession().save(test);
			}
			
			{
				Query query1 = LoadClasses.returnQueryByHQL(" From TestData test where test.name = :testName ");
				query1.setParameter("testName", this.nameSelected);
				
				List<TestData> list1 = query1.list();
				if(Util.isNullList(list1))
				{
					System.out.println("loaded object not found though SQL");
				}
				else
				{
					System.out.println("loaded object found though SQL");
				}
			}
			{
				SQLQuery query1 = Connection.getCurrentDBSession().createSQLQuery(" select test_id From Test where TEST_NAME = :testName ");
				query1.setParameter("testName", this.nameSelected);
				
				List<TestData> list1 = query1.list();
				if(Util.isNullList(list1))
				{
					System.out.println("loaded object not found though SQL");
				}
				else
				{
					System.out.println("loaded object found though SQL");
				}
				
			}
			
		Connection.getCurrentDBSession().flush();
			
		/*		{
				Query query1 = LoadClasses.returnQueryByHQL(" From TestData test where test.name = :testName ");
				query1.setParameter("testName", this.nameSelected);
				
				List<TestData> list1 = query1.list();
				if(Util.isNullList(list1))
				{
					System.out.println("loaded object not found after SQL insert and Flush");
				}
				else
				{
					System.out.println("loaded object found after SQL insert and Flush");
				}
			}*/
		}
		else
		{
			System.out.println("loaded object found");
		}
		
		return Constants.EMPTY_STRING;
		
	}

}
