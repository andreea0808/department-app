package com.example.Springboot.controller;

import com.example.Springboot.entity.Department;
import com.example.Springboot.error.DepartmentNotFoundException;
import com.example.Springboot.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    private final Logger LOGGER =
            LoggerFactory.getLogger( DepartmentController.class );

    @PostMapping
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info( "Inside saveDepartment of DepartmentController" );
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/count")
    public List<Department> fetchDepartmentList() {
        LOGGER.info( "Inside fetchDepartmentList of DepartmentController" );
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
       departmentService.deleteDepartmentById(departmentId);
       return "Department deleted Successfully!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId,
                                       @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName( departmentName );
    }
}
