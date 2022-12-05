package com.skypro.springandmockito.service.employee_service;


import com.skypro.springandmockito.model.Employee;

import com.skypro.springandmockito.repository.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl {
    private final Map<Integer, Employee> employees = new HashMap<>();

    private EmployeesRepository employeesRepository;

    public EmployeeServiceImpl() {
        this.employeesRepository = employeesRepository;
    }

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }//получить всех сотрудников

    public Employee addEmployee(Employee employee) {//добавление сотрудника
        if (getAllEmployees() == null || getAllEmployees() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        this.employees.put(employee.getId(), employee);//положить
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();//Получение суммы зарплат сотрудников
    }

    public Employee getEmployeeWithMinSalary() {//получить сотрудника с минимальной зарплатой
        return employeesRepository.getEmployees().stream().min(Comparator.comparingInt
                (Employee::getSalary)).orElseGet(() -> null);
    }

    public Employee getEmployeeWithMaxSalary() {//получить сотрудника с максимальной зарплатой
        return employeesRepository.getEmployees().stream().max(Comparator.comparingInt
                (Employee::getSalary)).orElseGet(() -> null);
    }
    public Object getSalaryAverage() {
        return employees.values().stream().mapToInt(Employee::getSalary).average();//средняя ЗП
    }

    public List<Employee> getTingAllEmployeesWhoseSalaryIsHigherThanTheAverageSalary() {
        double average = (double) getSalaryAverage();
        return (List<Employee>) employees.values().stream().filter(e -> e.getSalary() > average);
    }

//    private OptionalDouble averageSalary(List<Employee> employees) {
//        return employeesRepository.getEmployees().stream().mapToInt(Employee::getSalary).average();//средняя ЗП
//    }
//    public List<Employee> getEmployeeWithSalaryMoreAverage() {//получить сотрудника с зарплатой выше средней
//        final List<Employee> employees = employeesRepository.getEmployees();
//        final OptionalDouble average = averageSalary(employees);
//        return employees.stream().filter(e->e.getSalary()).average();//средняя ЗП
//    }
//    public OptionalDouble getHighSalary() {
//        return employees.values().stream().mapToInt(Employee::getSalary).filter(employee -> employee.getSalary()  >
//                getSalaryAverage()).collect(Collectors.toList());
//    }
}