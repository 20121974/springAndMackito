package com.skypro.springandmockito.department_service;

import com.skypro.springandmockito.model.Employee;
import com.skypro.springandmockito.repository.EmployeesRepository;
import com.skypro.springandmockito.service.department_service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeesRepository employeesRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    private List<Employee> actualEmployees;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 40_000, 1);
        Employee employee2 = new Employee(2, "Petr", "Petrov", 30_000, 2);
        Employee employee3 = new Employee(3, "Liza", "Sidorova", 25_000, 3);

        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3));
        when(employeesRepository.getEmployees()).thenReturn(actualEmployees);
    }
    @Test
    public void shouldReturnExistingDepartments() {
        final Set<Integer> actual = actualEmployees.stream().map(Employee::getDepartment).
                collect(Collectors.toSet());
        final Set<Integer> expected = departmentService.getExistingDepartments();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmployeeFromDepartments() {
        final int departmentId = 1;
        final List<Employee> actual = actualEmployees.stream().filter(employee -> employee.getId() == departmentId)
                .collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeeFromDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightSalarySumOfDepartment() {
        final int departmentId = 1;
        final int actual = actualEmployees.stream().mapToInt(Employee::getSalary).sum();
        final int expected = departmentService.getSalarySumOfDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightSalaryMaxDepartment() {
        final int departmentId = 1;
        final Employee actual = actualEmployees.stream().max(Comparator.comparingInt(Employee::getSalary)).get();
        final int expected = departmentService.getSalaryMaxDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRightSalaryMinDepartment() {
        final int departmentId = 1;
        final Employee actual = actualEmployees.stream().min(Comparator.comparingInt(Employee::getSalary)).get();
        final int expected = departmentService.getSalaryMinDepartment(departmentId);
        assertEquals(expected, actual);
    }

}
