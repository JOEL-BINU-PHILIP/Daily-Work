package com.example.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	private String id;
	@Field(name = "employee_name")
	private String empName;
	private String location;
	private BigDecimal salary;
}
