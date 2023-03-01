package com.hcc.ExcelToDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcc.ExcelToDatabase.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
