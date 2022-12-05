package com.skypro.springandmockito.repository;

import com.skypro.springandmockito.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeesRepository {

    private static int lastId;
    private final List<Employee> employees;

    public EmployeesRepository(List<Employee> employees) {
        this.employees = employees;

    }

    public static int getLastId() {
        return lastId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        ++lastId;
    }
}
