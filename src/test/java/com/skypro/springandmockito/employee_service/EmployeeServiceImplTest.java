package com.skypro.springandmockito.employee_service;


import com.skypro.springandmockito.model.Employee;
import com.skypro.springandmockito.repository.EmployeesRepository;
import com.skypro.springandmockito.service.department_service.DepartmentServiceImpl;
import com.skypro.springandmockito.service.employee_service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    @ParameterizedTest
    @MethodSource("salarySumTest")
    public void salarySumTest(int salary1, int salary2, int salary3, int result) {
        Assertions.assertEquals(result, employeeService.getSalarySum());
    }

    @MethodSource("employeeWithMinSalary")
    public void employeeWithMinSalaryTest(int salary1, int salary2, int salary3, int result) {
        Assertions.assertEquals(result, employeeService.getEmployeeWithMinSalary());
    }

    @MethodSource("employeeWithMaxSalary")
    public void employeeWithMaxSalaryTest(int salary1, int salary2, int salary3, int result) {
        Assertions.assertEquals(result, employeeService.getEmployeeWithMaxSalary());
    }

    public static List<Arguments> sumTest() {
        return List.of(
                Arguments.of(1000, 5000, 15000),
                Arguments.of(5000, 3000, 2000),
                Arguments.of(5000, 3000, 2000));
    }




}
