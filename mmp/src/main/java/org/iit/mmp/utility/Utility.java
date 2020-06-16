package org.iit.mmp.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
//import jxl.read.biff.BiffException;

public class Utility {

	static Random rand;
	 static XSSFWorkbook workbook;
	 static XSSFSheet sheet;
	public static String generateRandom(int n,int range)
	{
		rand = new Random();
		String str = "";
		for(int j=0; j<n; j++)
		{
			str = str+ 9;	 
		}
		System.out.println(str);

		return str+rand.nextInt(range);
	}

	/**
	 * This will choose a random state
	 * @return - state 
	 */

	public static String getRandomState(){

		String state = "";
		String[] sArray = new String [50];
		sArray[0] = "Alabama";sArray[1] = "Alaska";sArray[2] = "Arizona";sArray[3] = "Arkansas";sArray[4] = "California";
		sArray[5] = "Colorado";sArray[6] = "Connecticut";sArray[7] = "Delaware";sArray[8] = "Florida";sArray[9] = "Georgia";
		sArray[10] = "Hawaii";sArray[11] = "Idaho";sArray[12] = "Illinois";sArray[13] = "Indiana";sArray[14] = "Iowa";
		sArray[15] = "Kansas";sArray[16] = "Kentucky";sArray[17] = "Louisiana";sArray[18] = "Maine";sArray[19] = "Maryland";
		sArray[20] = "Massachusetts";sArray[21] = "Michigan";sArray[22] = "Minnesota";sArray[23] = "Mississippi";sArray[24] = "Missouri";
		sArray[25] = "Montana";sArray[26] = "Nebraska";sArray[27] = "Nevada";sArray[28] = "New Hampshire";sArray[29] = "New Jersey";
		sArray[30] = "New Mexico";sArray[31] = "New York";sArray[32] = "North Carolina";sArray[33] = "North Dakota";sArray[34] = "Ohio";
		sArray[35] = "Oklahoma";sArray[36] = "Oregon";sArray[37] = "Pennsylvania";sArray[38] = "Rhode Island";sArray[39] = "South Carolina";
		sArray[40] = "South Dakota";sArray[41] = "Tennessee";sArray[42] = "Texas";sArray[43] = "Utah";sArray[44] = "Vermont";
		sArray[45] = "Virginia";sArray[46] = "Washington";sArray[47] = "West Virginia";sArray[48] = "Wisconsin";sArray[49] = "Wyoming";
		int num = rand.nextInt(49);
		state = sArray[num];
		System.out.println(state);		
		return state;
	}

	public static int getRandomNoOfDigits(int noOfDigits){

		rand = new Random();
		int addend1=0, addend2=0; String zero ="";String bound = "";int result=0;
		for(int i=1;i<=(noOfDigits-1); i++){
			zero = zero+0;
		}
		zero = 1+zero;
		addend1 = Integer.parseInt(zero);
		for (int j=1; j<=(noOfDigits-1); j++){
			bound = bound+9;
		}
		bound = 8+bound;
		addend2 = Integer.parseInt(bound);
		result = addend1+ rand.nextInt(addend2);
		System.out.println("Random "+noOfDigits+ " digit number is : "+result);
		return result;

	}

	/**
	 * This is to generate a random string for input data
	 * @param -int value of total no. of characters need to be in the input data
	 * @return - a random String
	 */
	public static String getRandomString(int noOfChars){

		rand = new Random();
		String s = "";
		int bound = noOfChars;
		//char[] charArray = new char[bound];
		for(int i=0; i<bound; i++){
			char c = (char) ('a'+rand.nextInt(26));
			s = s+c;
		}
		System.out.println("getRandomString Method returning "+s);
		return s;
	}

	public static String[][] readXls(String filePath) throws Exception, IOException{

		File srcFile = new File(filePath);
		Workbook wb = Workbook.getWorkbook(srcFile);
		Sheet sheet = wb.getSheet("MMPLogin");
		int row = sheet.getRows();
		int col = sheet.getColumns();

		String[][] str = new String[row][col];

		for (int i=0; i<row; i++){
			for (int j=0;j<col; j++){

				Cell cell = sheet.getCell(j, i);
				str[i][j] = cell.getContents().toString();
			}
		}

		return str;		
	}

	public static String[][] readXlsx(String filePath) throws IOException{

		File srcFile = new File(filePath);
		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int row = sheet.getLastRowNum()+1;
		int col = 2;
		String[][] str = new String[row][col];
		for (int i=0; i<row; i++){
			str[i][0] = sheet.getRow(i).getCell(0).toString();
			str[i][1] = sheet.getRow(i).getCell(1).toString();
		}
		wb.close();
		return str;		

	}
	public static String getFutureDate(int days,String pattern)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(d);
		return date;
	}
	public  static String getFutureDate(int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		String date = sdf.format(d);
		return date;
	}


	//READING SPREADSHEET
	public static void ExcelUtils(String excelPath, String sheetName) {
		try {
			//creating a reference variable for workbook and sheet.
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Row count from excel sheet
	public static int getRowCount() {
		int rowCount = 0;
		try {
			rowCount = sheet.getLastRowNum()+1;
			System.out.println("No of Rows: " + rowCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return rowCount;
	}

	// Total number of columns 
	public static int getcolCount() {
		int colCount = 0;
		try 
		{
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		} 
		catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return colCount;
	}
	
	public static String getCellDataString(int rowCount, int colCount) {
		String cellData = null;
		try {

			cellData = sheet.getRow(rowCount).getCell(colCount).getStringCellValue();
			//System.out.println("Using Excel util"+cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return cellData;
	}
}
