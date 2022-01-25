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

    static final String GET_ALL = String.format("SELECT * FROM %s", EMPLOYEE_TABLE);
    static final String GET_BY_ID = String.format("SELECT * FROM %s WHERE %s=?", EMPLOYEE_TABLE, ID);
    static final String SAVE = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)",
            EMPLOYEE_TABLE,
            FIRST_NAME,
            LAST_NAME,
            DEPARTMENT_ID,
            JOB_TITLE,
            GENDER,
            DATE_OF_BIRTH);
    static final String UPDATE = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?" +
                    " WHERE %s=?",
            EMPLOYEE_TABLE,
            FIRST_NAME,
            LAST_NAME,
            DEPARTMENT_ID,
            JOB_TITLE,
            GENDER,
            DATE_OF_BIRTH,
            ID);
    static final String DELETE = String.format("DELETE FROM employee WHERE %s=?", ID);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RowMapper<Employee> employeeRowMapper;

    public Employee getOne(Integer id) {
        List<Employee> employeeList = jdbcTemplate.query(GET_BY_ID, employeeRowMapper, id);
        int firstElementIndex = 0;

        return (employeeList.isEmpty()) ? null : employeeList.get(firstElementIndex);
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query(GET_ALL, employeeRowMapper);
    }

    public Employee save(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE, new String[]{ID});

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setLong(3, employee.getDepartmentId());
            ps.setString(4, employee.getJobTitle());
            ps.setString(5, employee.getGender().toString());
            ps.setDate(6, employee.getDateOfBirth());

            return ps;
        }, keyHolder);

        return getOne(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public Employee update(Employee employee) {
        jdbcTemplate.update(UPDATE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().toString(),
                employee.getDateOfBirth(),
                employee.getEmployeeId());

        return getOne(employee.getEmployeeId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }
}
