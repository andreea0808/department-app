package com.example.Springboot.repository;

import com.example.Springboot.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@SpringBootTest(classes = DepartmentRepository.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                .departmentName( "Mechanical Engineering" )
                .departmentCode( "ME - 011" )
                .departmentAddress( "Bucharest" )
                .build();
        entityManager.persist( department );
    }

    @Test
    public void whenFindByIdThenReturnDepartment() {
        Department department = departmentRepository.findById(1l).get();
        assertEquals( department.getDepartmentName(), "Mechanical Engineering" );
    }
}