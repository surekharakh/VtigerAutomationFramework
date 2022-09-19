package vtiger.GenericUtility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
/**
 * This class contains all the generic methods related to database
 * @author Garvit16
 *
 */
public class DataBaseUtility {

	Driver driverRef;
	Connection con=null;
	/**
	 * This method will establish connection with database
	 * @throws SQLException
	 */
	public void ConnectToDB() throws SQLException
	{
		driverRef=new Driver();//to connect wid DB first we create object of driver
		DriverManager.registerDriver(driverRef);//then register the driver with driverref and then connect withDB using getconnection method
		con=DriverManager.getConnection(IConstantUtility.DBUrl, IConstantUtility.DBUsername, IConstantUtility.DBPassword);// Instead of hard coding we call it from Iconstant utility
		
	}
	/**
	 * This method is used to close the database connection
	 * @throws SQLException
	 */
	
	public void CloseDB() throws SQLException
	{
		con.close();  //to close the db
	}
	
	/**
	 * \This method will execute the query and verify the expected data in database and return the data only if exp
	 * data and actual data is matching else it will return empty string
	 * @param query
	 * @param coloumnIndex
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	
	public String executeQueryVerifyDataAndReturn(String query,int coloumnIndex,String expData) throws SQLException
	{
		//execute a querry
		boolean flag=false;
		ResultSet result = con.createStatement().executeQuery(query);   //to execute a querry we call createstatement and execute querry
		 //from databaseref con
		while(result.next())
		{
			String actData = result.getString(coloumnIndex);// to get the value from cell by passing column index
			if(actData.equals(expData)) // verify expdata with actual data from database ,for comparison we use equals
//we cannot use contains bcz if only one char is matching then it will say TS is pass
//so this is not a right way,so we use equals if it is equal then i have to return the value.
//before returning we should break. if condition is true then we raise flag=true. Then if it is true then we have to return exp data
//and if expdata and actdata is differant means flag is false then return ""null.
				
			{
				flag=true;
				break;  //after break and return we can not add a statement so we have to raise the flag,so we declare the variable boolean flag=false initially
				
				
			}
// why we dont use return inside a while loop,bcz by using next everytime loop is running until find next variable  and we dont know at what time we get true means expdata is equal to actual data
//	3 times else will execute if i found at 4th data my if condition is true ,so else part data is also return so to avoid that we use return outside the for loop 	
		}
		//return if exp data and act data is matching
		if(flag)
		{
			System.out.println("data verified");
			return expData; //when flag is true then return
		}
		else
		{
			System.out.println("data not verified");
			return "";
		}
		}
}

