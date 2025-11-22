package com.example.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{
    
}
