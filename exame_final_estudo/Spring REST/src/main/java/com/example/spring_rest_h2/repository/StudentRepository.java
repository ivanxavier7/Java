package com.example.spring_rest_h2.repository;

import org.springframework.data.repository.CrudRepository;  
import com.example.spring_rest_h2.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>  
{  
}  