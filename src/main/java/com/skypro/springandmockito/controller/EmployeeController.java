package com.skypro.springandmockito.controller;
import com.skypro.springandmockito.model.Employee;
import com.skypro.springandmockito.service.employee_service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;//поле

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }


    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {//получить всех сотрудников из базы сотрудников с использованием метода сервиса получить
        return this.employeeServiceImpl.getAllEmployees();
    }

    @PatchMapping("/employees")
    public Employee createEmployee(Employee employee) {//добавить сотрудника в базу сотрудников с использованием метода сервиса добавить
        return this.employeeServiceImpl.addEmployee(employeeServiceImpl.addEmployee(employee));
    }

    @GetMapping("/employees/salary/sum")//получить сумму ЗП
    public int getSalarySum() {
        return this.employeeServiceImpl.getSalarySum();
    }

    @GetMapping("/employees/salary/MinSalary")//получить минимальную ЗП
    public Employee getEmployeeWithMinSalary() {
        return this.employeeServiceImpl.getEmployeeWithMinSalary();
    }

    @GetMapping("/employees/salary/MaxSalary")//получить максимальную ЗП
    public Employee getEmployeeWithMaxSalary() {
        return this.employeeServiceImpl.getEmployeeWithMaxSalary();
    }

    @GetMapping("/employees/salary/SalaryAverage")//получить среднюю ЗП
    public Object getSalaryAverage() {
        return this.employeeServiceImpl.getSalaryAverage();
    }

    @GetMapping("/employees/salary/high-salary")//получить сотрудников с ЗП выше средней
    public Object getTingAllEmployeesWhoseSalaryIsHigherThanTheAverageSalary() {
        return this.employeeServiceImpl.getTingAllEmployeesWhoseSalaryIsHigherThanTheAverageSalary();
    }
}
