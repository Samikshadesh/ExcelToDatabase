package com.hcc.ExcelToDatabase.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcc.ExcelToDatabase.entity.Customer;
import com.hcc.ExcelToDatabase.repository.CustomerRepository;
@Service
public class CustomerService {	
	@Autowired
	private CustomerRepository customerRepository;
	//extract data and store it into database
	public void saveCustomerToDatabase(MultipartFile file) {
		
		//before uploading  validate file
		if(ExcelUploadService.isValidExcelFile(file))
		{
			try {
				//get information from xlsx file
				List<Customer>customers = ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
				this.customerRepository.saveAll(customers);//save data into database
				
			} catch (IOException e) {
				throw new IllegalArgumentException("The file is not valid excel file");
			}
		}
	}
	//retrive record form database
	public List<Customer>getCustomers(){
		return customerRepository.findAll();
	}

}
