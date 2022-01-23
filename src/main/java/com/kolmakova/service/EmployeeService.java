package com.kolmakova.service;

import com.kolmakova.dao.EmployeeDao;
import com.kolmakova.dto.Employee;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee getById(Long id){
        return employeeDao.getById(id);
    }

    public List<Employee> getAll(){
        return employeeDao.getAllEmployees();
    }

    public Employee save(Employee employee){
        return employeeDao.addEmployee(employee);
    }

    public Employee update(Employee employee){
        employeeDao.updateEmployee(employee);
        return getById(employee.getEmployeeId());
    }

    public void delete(Long id){
        employeeDao.deleteEmployee(id);
    }
}
