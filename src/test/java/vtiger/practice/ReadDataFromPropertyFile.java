package vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
// step1: load the file location
		FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
// step2: create object of properties
		
		Properties pob= new Properties();
		
// Step3: load the data into propeties
		
		pob.load(fis);
		
// Step 4: use the key and read the value
		
		String browser = pob.getProperty("Browser");
		
		System.out.println(browser);
		
		String url = pob.getProperty("url");
		
		System.out.println(url);
		
		
		
	}

}
