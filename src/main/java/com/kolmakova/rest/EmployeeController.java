package com.kolmakova.rest;

import com.kolmakova.dto.Employee;
import com.kolmakova.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping
    public List<Employee> readAll() {
        return employeeService.getAll();
    }

    @GetMapping("{id}")
    public Employee readOne(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.update(id, employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
