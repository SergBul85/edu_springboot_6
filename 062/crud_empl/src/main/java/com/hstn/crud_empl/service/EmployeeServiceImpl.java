package com.hstn.crud_empl.service;

import com.hstn.crud_empl.dao.EmployeeRepository;
import com.hstn.crud_empl.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        Employee newEmployee = null;
        if (employee.isPresent()) {
            newEmployee = employee.get();
        } else {
            throw new RuntimeException("No employee with id=" + id);
        }
        return newEmployee;
    }

    @Override
    public Employee save(Employee employees) {
        return employeeRepository.save(employees);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }
}
