package com.kolmakova.service;

import com.kolmakova.dao.EmployeeDao;
import com.kolmakova.dto.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void getByIdExistingEmployee() {
        //GIVEN
        Integer existingEmployeeId = 1;
        Employee expected = mock(Employee.class);

        //WHEN
        when(employeeDao.getOne(existingEmployeeId)).thenReturn(expected);
        Employee actual = employeeService.getOne(existingEmployeeId);

        //THEN
        verify(employeeDao).getOne(existingEmployeeId);
        assertEquals(expected, actual);
    }

    @Test
    public void getByIdNotExistingEmployee() {
        //GIVEN
        Integer notExistingEmployeeId = 20;

        //WHEN
        when(employeeDao.getOne(notExistingEmployeeId)).thenReturn(null);
        Employee actual = employeeService.getOne(notExistingEmployeeId);

        //THEN
        verify(employeeDao).getOne(notExistingEmployeeId);
        assertNull(actual);
    }

    @Test
    public void getAll() {
        //GIVEN
        List<Employee> expected = new ArrayList<>();

        //WHEN
        when(employeeDao.getAll()).thenReturn(expected);
        List<Employee> actual = employeeService.getAll();

        //THEN
        verify(employeeDao).getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void save() {
        //GIVEN
        Employee expected = mock(Employee.class);

        //WHEN
        when(employeeDao.save(expected)).thenReturn(expected);
        Employee actual = employeeService.save(expected);

        //THEN
        verify(employeeDao).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        //GIVEN
        Employee expected = mock(Employee.class);

        //WHEN
        when(employeeDao.update(expected)).thenReturn(expected);
        Employee actual = employeeService.update(expected);

        //THEN
        verify(employeeDao).update(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        //GIVEN
        int employeeId = 1;

        //WHEN
        employeeService.delete(employeeId);

        //THEN
        verify(employeeDao).delete(employeeId);
    }
}
