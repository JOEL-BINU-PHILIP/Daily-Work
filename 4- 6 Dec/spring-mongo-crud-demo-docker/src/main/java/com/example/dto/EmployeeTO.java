package com.example.dto;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTO {
    @Id
    private String id;
    private String empName;
    private String location;
    private BigDecimal salary;
}
