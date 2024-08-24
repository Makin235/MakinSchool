package com.makin.makinschool.repository;

import com.makin.makinschool.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByOrderByNameDesc();
    List<Course> findByOrderByName();
}
