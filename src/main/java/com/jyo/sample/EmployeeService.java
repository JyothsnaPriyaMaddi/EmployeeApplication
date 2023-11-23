package com.jyo.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	List<Employee> employeeList = new ArrayList<>();
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public void createEmployees(@RequestBody Employee employee) {
		employeeRepository.save(employee);
	}
	public void updateEmployee(int id, Employee updateEmployee) {
		Optional<Employee> existingEmployeeList = employeeRepository.findById(id);

		if(existingEmployeeList != null) {
            Employee existingEmployee = existingEmployeeList.get();

            // Update the existing employee fields
            existingEmployee.setEmployeeName(updateEmployee.getEmployeeName());
            existingEmployee.setEmployeeCountry(updateEmployee.getEmployeeCountry());

            // Save the updated employee to the repository
            employeeRepository.save(existingEmployee);
        } else {
            // Handle the case where the employee with the given ID is not found
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
    }
	
	public List<Employee> deleteEmployee(@PathVariable int id) {
		employeeRepository.deleteById(id);
		return employeeList;
	}

}
