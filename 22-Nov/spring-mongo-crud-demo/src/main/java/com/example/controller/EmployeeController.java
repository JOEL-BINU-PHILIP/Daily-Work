package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeTO;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public String createEmployee(@RequestBody EmployeeTO employeeTO) {
	    return employeeService.createEmployee(employeeTO);
	}

}
