package com.example.springbootmvc.service;

import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.dto.EmployeeDTO;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.entity.Employee;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse add(EmployeeDTO employeeDTO) {
        Optional<Department> optionalDepartment = departmentRepository.findById(employeeDTO.getDepartmentId());
        if (optionalDepartment.isEmpty()) return new ApiResponse("There is no such id", false);
        Department department = optionalDepartment.get();

        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(department);

        Employee save = employeeRepository.save(employee);
        return new ApiResponse("Saved", true, save);
    }
}
