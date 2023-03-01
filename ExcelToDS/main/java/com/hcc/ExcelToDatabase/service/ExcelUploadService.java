package com.hcc.ExcelToDatabase.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hcc.ExcelToDatabase.entity.Customer;

public class ExcelUploadService {
	//is file in valid excel file format or not
	public static boolean isValidExcelFile(MultipartFile file) {
		
		
		return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	//file upload,InputStreamfrom java.io package
	public static List<Customer>getCustomersDataFromExcel(InputStream inputStream){
		//list of customers
		List<Customer> customers=new ArrayList<>();
		try 
		{
			XSSFWorkbook workbook= new XSSFWorkbook(inputStream);//workbook contain multiple sheets-data
			XSSFSheet sheet= workbook.getSheet("CustomerData");//take specific sheet(name) from workbook
			
			int rowIndex=0;
			//row iteration as per sheet using foreach loop
			for (Row row : sheet) 
			{
				//header row table is already created don't want header row, ignore this
				if(rowIndex==0)
				{
					rowIndex++;//row index index by one
					continue;
				}
				//column iteration
				Iterator<Cell>cellIterator = row.iterator();//iterate cells from Each row
				int cellIndex = 0;//checking index of cell
				Customer customer = new Customer();
				//upto row contains cell
				while(cellIterator.hasNext())
				{
					//take data from cell setting it as per the cell Index
					Cell cell = cellIterator.next();//appropriate data sending as per data type(int,string)
					switch(cellIndex) {
					case 0 -> customer.setCustomerId((int) cell.getNumericCellValue());//get and set to this customer obj
					case 1->customer.setFirstName(cell.getStringCellValue());//get and set to this specific cell
					case 2->customer.setLastName(cell.getStringCellValue());
					case 3->customer.setCountry(cell.getStringCellValue());
					default->{
						}
					}
					cellIndex++;
				
				}
				customers.add(customer);//add that customer in customers list
				
			}
		}catch(IOException e)
		{
			e.getStackTrace();
		}
		return customers;
		
	}

}
