package com.example.demo.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.payroll.entities.Employee;


interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
