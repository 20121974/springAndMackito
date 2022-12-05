package com.skypro.springandmockito.controller;

import com.skypro.springandmockito.model.Employee;
import com.skypro.springandmockito.service.department_service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentService = departmentServiceImpl;
    }

    @GetMapping()
    public String getExistingDepartments() {
        return "Existing departments: " + departmentService.getExistingDepartments().toString();
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeFromDepartment(@PathVariable("id") int departmentId) {
        return (List<Employee>) departmentService.getEmployeeFromDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumOfDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getSalarySumOfDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public int getSalaryMinDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getSalaryMinDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public int getSalaryMaxDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getSalaryMaxDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeeByDepartment() {
        return departmentService.getEmployeeByDepartments();
    }
}
