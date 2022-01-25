package com.kolmakova.util;

import com.kolmakova.dto.Employee;
import com.kolmakova.dto.Gender;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

import static com.kolmakova.util.EmployeeConstants.*;

@Component
public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(rs.getInt(ID));
        employee.setFirstName(rs.getString(FIRST_NAME));
        employee.setLastName(rs.getString(LAST_NAME));
        employee.setDepartmentId(rs.getInt(DEPARTMENT_ID));
        employee.setJobTitle(rs.getString(JOB_TITLE));
        employee.setGender(Objects.nonNull(rs.getString(GENDER)) ? Gender.valueOf(rs.getString(GENDER).toUpperCase(Locale.ENGLISH)) : null);
        employee.setDateOfBirth(rs.getDate(DATE_OF_BIRTH));

        return employee;
    }
}
