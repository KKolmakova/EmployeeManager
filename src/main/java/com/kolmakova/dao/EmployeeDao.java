package com.kolmakova.dao;

import com.kolmakova.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kolmakova.util.EmployeeConstants.*;

@Repository
public class EmployeeDao {

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM " + EMPLOYEE_TABLE;
    private static final String SELECT_BY_ID = "SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + ID + " = ?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO " + EMPLOYEE_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE " + EMPLOYEE_TABLE + " SET (" + FIRST_NAME + " = ?, " +
            LAST_NAME + " = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ?) WHERE id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RowMapper<Employee> employeeRowMapper;

    public Employee getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, employeeRowMapper, id);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(SELECT_ALL_EMPLOYEES, employeeRowMapper);
    }

    public void addEmployee(Employee employee) {
        jdbcTemplate.update(INSERT_EMPLOYEE, null, convertEmployeeToString(employee));
    }

    public void updateEmployee(Long id, Employee employee) {
        jdbcTemplate.update(UPDATE_EMPLOYEE, convertEmployeeToString(employee), id);
    }

    public void deleteEmployee(Long id) {
        jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }

    private String convertEmployeeToString(Employee employee) {
        return employee.getFirstName() +
                employee.getLastName() +
                employee.getDepartmentId() +
                employee.getJobTitle() +
                employee.getGender() +
                employee.getDateOfBirth();
    }
}
