package com.skypro.springandmockito.department_service;

import com.skypro.springandmockito.model.Employee;
import com.skypro.springandmockito.repository.EmployeesRepository;
import com.skypro.springandmockito.service.department_service.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
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
        Employee employee2 = new Employee(2, "Ivan", "Ivanov", 35_000, 1);
        Employee employee3 = new Employee(3, "Ivan", "Ivanov", 40_000, 2);
        Employee employee4 = new Employee(4, "Petr", "Petrov", 30_000, 2);
        Employee employee5 = new Employee(5, "Liza", "Sidorova", 50_000, 3);
        Employee employee6 = new Employee(6, "Liza", "Sidorova", 45_000, 3);

        actualEmployees = new ArrayList<>(List.of(
                employee1,
                employee2,
                employee3,
                employee4,
                employee5,
                employee6));
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
        Assertions.assertEquals(25_000, departmentService.getSalarySumOfDepartment(1));
    }

    @Test
    public void shouldReturnRightSalaryMaxDepartment() {
        Assertions.assertEquals(25_000, departmentService.getSalaryMaxDepartment(2));
    }

    @Test
    public void shouldReturnRightSalaryMinDepartment() {
        Assertions.assertEquals(25_000, departmentService.getSalaryMinDepartment(3));
    }

}
