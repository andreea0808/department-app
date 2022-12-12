package com.example.Springboot.controller;

import com.example.Springboot.entity.Department;
import com.example.Springboot.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress( "Bucharest" )
                .departmentCode( "IT-06" )
                .departmentName( "IT" )
                .departmentId( 1L )
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = department = Department.builder()
                .departmentAddress( "Bucharest" )
                .departmentCode( "IT-06" )
                .departmentName( "IT" )
                .departmentId( 1L )
                .build();

        Mockito.when( departmentService.saveDepartment( inputDepartment ) )
                .thenReturn( department );
        mockMvc.perform( post( "/departments" )
                .contentType( MediaType.APPLICATION_JSON )
                .content("{\n" +
                         "\t\"departmentName\": \"IT\", \n" +
                         "\t\"departmentAddress\": \"Bucharest\", \n" +
                         "\t\"departmentCode\": \"IT-06\"\n" +
                         "}" ))
                .andExpect( status().isOk() );
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when( departmentService.fetchDepartmentById( 1L ) )
                .thenReturn( department );

        mockMvc.perform( get( "/departments/1" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.departmentName" ).value( department.getDepartmentName() ));
    }
}