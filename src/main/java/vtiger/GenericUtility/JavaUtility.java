package vtiger.GenericUtility;
import java.util.Date;
import java.util.Random;
/**
 *This class contain all generic methods related to java 

 * @author Garvit16
 *
 */

public class JavaUtility {
	
	/**
	 * This method will generate random integer number for every execution 
	 * @return
	 */
		public int RandomNumber()
		{
			Random r=new Random();
			int ran=r.nextInt(1000);
			return ran;
		}
		/**
		 * This method will return the current system date 
		 * @return
		 */
		public String getSystemDate() {
			Date date=new Date();
			String d=date.toString();
			return d;
		}
		
		/**
		 * This method will be provide the date and time in specific format
		 * @return
		 */
		
		public String getSystemDateFormat() {
			Date d=new Date();
			String[] dArr=d.toString().split(" ");
			String date=dArr[2];
			String month=dArr[1];
			String year=dArr[5];
			String time=dArr[3].replace(":", "_");
			String currentdate=date+" "+month+ " "+year +" "+time;
			return currentdate;
		}


}
