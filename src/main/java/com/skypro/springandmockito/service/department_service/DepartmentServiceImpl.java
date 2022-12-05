package com.skypro.springandmockito.service.department_service;

import com.skypro.springandmockito.model.Employee;
import com.skypro.springandmockito.repository.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl {
    private final EmployeesRepository employeesRepository;


    public DepartmentServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }


    public Set<Integer> getExistingDepartments() {//получить существующие отделы
        return employeesRepository.getEmployees().stream().map(Employee::getDepartment).
                collect(Collectors.toSet());
    }


    public List<Employee> getEmployeeFromDepartment(int departmentId) {//получить сотрудника из отдела
        return employeesRepository.getEmployees().stream().filter(employee -> employee.getId() == departmentId)
                .collect(Collectors.toList());
    }

    public int getSalarySumOfDepartment(int departmentId) {//получить сумму ЗП Отдела
        return getEmployeeFromDepartment(departmentId).stream().mapToInt(Employee::getSalary).sum();
    }

    public int getSalaryMaxDepartment(int departmentId) {
        return getEmployeeFromDepartment(departmentId).stream().mapToInt(Employee::getSalary)
                .max().orElseThrow();
    }

    public int getSalaryMinDepartment(int departmentId) {
        return getEmployeeFromDepartment(departmentId).stream().mapToInt(Employee::getSalary)
                .min().orElseThrow();
    }
    public Map<Integer, List<Employee>> getEmployeeByDepartments() {//устраивайтесь на работу В Отделы
        return getExistingDepartments().stream().collect(Collectors.toMap(dept -> dept, this::getEmployeeFromDepartment));
    }
}


