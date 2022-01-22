package com.kolmakova.service;

import com.kolmakova.dao.EmployeeDao;
import com.kolmakova.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void save(Employee employee){
        employeeDao.addEmployee(employee);
    }

    public void update(Long id, Employee employee){
        employeeDao.updateEmployee(id, employee);
    }

    public void delete(Long id){
        employeeDao.deleteEmployee(id);
    }
}
