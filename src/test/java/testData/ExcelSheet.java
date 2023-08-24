package testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelSheet {

	static Workbook book;
	static Sheet sheet;

	public static Object[][] readdata(String Sheetname){
		FileInputStream file = null;

		try {
			file = new FileInputStream("C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\testData\\Details.xlsx");
			book = WorkbookFactory.create(file); //HSSF: older version of excel //XSSF new excel
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException a) {
			a.printStackTrace();
		}

		/*
		 * try { book = WorkbookFactory.create(file); //HSSF: older version of excel
		 * //XSSF new excel } catch(IOException a) { a.printStackTrace(); }
		 */

		sheet = book.getSheet(Sheetname);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for(int i=0; i<sheet.getLastRowNum(); i++) {

			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++ )
			{ 
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}

		return data;

	}
}
