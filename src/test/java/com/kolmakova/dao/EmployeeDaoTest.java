package com.kolmakova.dao;

import com.kolmakova.dto.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static com.kolmakova.dao.EmployeeDao.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;
    @Mock
    RowMapper<Employee> employeeRowMapper;
    @InjectMocks
    EmployeeDao employeeDao;

    @Before
    public void setJdbcTemplate() {
        ReflectionTestUtils.setField(employeeDao, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void getOne() {
        //GIVEN
        int employeeId = 1;
        int firstElementIndex = 0;

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        List<Employee> expected = new ArrayList<>();
        expected.add(employee);

        //WHEN
        when(jdbcTemplate.query(GET_BY_ID, employeeRowMapper, employeeId)).thenReturn(expected);
        Employee actual = employeeDao.getOne(employeeId);

        //THEN
        assertEquals(expected.get(firstElementIndex), actual);
    }

    @Test
    public void getAll() {
        //GIVEN
        List<Employee> expected = mock(List.class);

        //WHEN
        when(jdbcTemplate.query(GET_ALL, employeeRowMapper)).thenReturn(expected);
        List<Employee> actual = employeeDao.getAll();

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        //GIVEN
        int employeeId = 1;

        //WHEN
        employeeDao.delete(employeeId);

        //THEN
        verify(jdbcTemplate).update(DELETE, employeeId);
    }
}
