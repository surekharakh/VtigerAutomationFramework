package vtiger.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 *This class contains all the generic methods related to excel sheet

 * @author Garvit16
 *
 */
public class ExcelFileUtility {
	
	/**
	 * This method will read the data from excel sheet with sheet name,row and cell number
	 * @param sheet
	 * @param row
	 * @param cel
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
		public String readDataFromExcel(String sheet,int row,int cel) throws EncryptedDocumentException, IOException{
			FileInputStream fis=new FileInputStream(IConstantUtility.excelFilePath);
			//Step2 : Creat a workbook
			Workbook wb = WorkbookFactory.create(fis);
			//Step 3::: Get into the sheet
			Sheet sh = wb.getSheet(sheet);
			//Step 4::: Get into the Row
			Row rw = sh.getRow(row);
			//Step 5::: Get into the cell
			Cell c = rw.getCell(cel);
			//Step 6 :: Read the value present in the cell
			String value = c.getStringCellValue();
			return value;
		}
		/**
		 * This method will return total number of row in sheet
		 * @param sheet
		 * @return
		 * @throws EncryptedDocumentException
		 * @throws IOException
		 */
		public int getRowCount(String sheet) throws EncryptedDocumentException, IOException {
			FileInputStream fis=new FileInputStream(IConstantUtility.excelFilePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheet);
			int data=sh.getLastRowNum();
			wb.close();
			return data;
		}
		/**
		 * This method is write the data into excelsheet
		 * @param sheet
		 * @param row
		 * @param cel
		 * @param value
		 * @throws EncryptedDocumentException
		 * @throws IOException
		 */
		public void writeDataIntoExcel(String sheet,int row,int cel,String value) throws EncryptedDocumentException, IOException {
			FileInputStream fis=new FileInputStream(IConstantUtility.excelFilePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheet);
			Row r=sh.getRow(row);
			Cell c=r.getCell(cel);
			c.setCellValue(value);
			
			FileOutputStream fout=new FileOutputStream(IConstantUtility.excelFilePath);
			wb.write(fout);
			wb.close();
		}
}
