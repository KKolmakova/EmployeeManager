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

    public Employee getOne(Integer id) {
        return employeeDao.getOne(id);
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeDao.update(employee);
    }

    public void delete(Integer id) {
        employeeDao.delete(id);
    }
}
