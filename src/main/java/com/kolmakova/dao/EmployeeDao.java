package com.kolmakova.dao;

import com.kolmakova.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

import static com.kolmakova.util.EmployeeConstants.*;

@Repository
public class EmployeeDao {

    private static final String SELECT_ALL_EMPLOYEES = String.format("SELECT * FROM %s", EMPLOYEE_TABLE);
    private static final String SELECT_BY_ID = String.format("SELECT * FROM %s WHERE %s=?", EMPLOYEE_TABLE, ID);
    private static final String INSERT_EMPLOYEE = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) " +
                    "VALUES (?, ?, ?, ?, ?, ?)",
            EMPLOYEE_TABLE,
            FIRST_NAME,
            LAST_NAME,
            DEPARTMENT_ID,
            JOB_TITLE,
            GENDER,
            DATE_OF_BIRTH);
    private static final String UPDATE_EMPLOYEE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?" +
            " WHERE %s=?", EMPLOYEE_TABLE, FIRST_NAME, LAST_NAME, DEPARTMENT_ID, JOB_TITLE, GENDER, DATE_OF_BIRTH, ID);
    private static final String DELETE_EMPLOYEE = String.format("DELETE FROM employee WHERE %s=?", ID);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RowMapper<Employee> employeeRowMapper;

    public Employee getById(Long id) {
        List<Employee> employeeList = jdbcTemplate.query(SELECT_BY_ID, employeeRowMapper, id);

        return (employeeList.isEmpty()) ? null : employeeList.get(0);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(SELECT_ALL_EMPLOYEES, employeeRowMapper);
    }

    public Employee addEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE, new String[]{ID});

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setLong(3, employee.getDepartmentId());
            ps.setString(4, employee.getJobTitle());
            ps.setString(5, employee.getGender().toString());
            ps.setDate(6, employee.getDateOfBirth());

            return ps;
        }, keyHolder);

        return getById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    public void updateEmployee(Employee employee) {
        jdbcTemplate.update(UPDATE_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().toString(),
                employee.getDateOfBirth(),
                employee.getEmployeeId());
    }

    public void deleteEmployee(Long id) {
        jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }
}
