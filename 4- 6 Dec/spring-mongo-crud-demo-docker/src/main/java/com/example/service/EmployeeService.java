package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.EmployeeRepository;
import com.example.dto.EmployeeTO;
import com.example.model.Employee;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
	public String createEmployee(EmployeeTO employeeTO) {
		try {
			Employee emp = Employee.builder()
					.empName(employeeTO.getEmpName())
					.salary(employeeTO.getSalary())
					.location(employeeTO.getLocation())
					.build();
			employeeRepository.save(emp);
		} catch (Exception e) {
			
		}
		return "Employee Created Succesfully";
	}

}
