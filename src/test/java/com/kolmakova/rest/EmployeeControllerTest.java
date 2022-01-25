package com.kolmakova.rest;

import com.kolmakova.dto.Employee;
import com.kolmakova.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    EmployeeController employeeController;

    @Test
    public void getOne() {
        //GIVEN
        Integer existingEmployeeId = 10;
        Employee expected = mock(Employee.class);

        //WHEN
        when(employeeService.getOne(existingEmployeeId)).thenReturn(expected);
        Employee actual = employeeController.getOne(existingEmployeeId);

        //THEN
        verify(employeeService).getOne(existingEmployeeId);
        assertEquals(expected, actual);
    }

    @Test
    public void getAll() {
        //GIVEN
        List<Employee> expected = new ArrayList<>();

        //WHEN
        when(employeeService.getAll()).thenReturn(expected);
        List<Employee> actual = employeeController.getAll();

        //THEN
        verify(employeeService).getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        //GIVEN
        Employee expected = mock(Employee.class);

        //WHEN
        when(employeeService.save(expected)).thenReturn(expected);
        Employee actual = employeeController.create(expected);

        //THEN
        verify(employeeService).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        //GIVEN
        Employee expected = mock(Employee.class);

        //WHEN
        when(employeeService.update(expected)).thenReturn(expected);
        Employee actual = employeeController.update(expected);

        //THEN
        verify(employeeService).update(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        //GIVEN
        int employeeId = 1;

        //WHEN
        employeeController.delete(employeeId);

        //THEN
        verify(employeeService).delete(employeeId);
    }
}
