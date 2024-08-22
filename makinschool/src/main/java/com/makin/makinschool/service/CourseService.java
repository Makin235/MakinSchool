package com.makin.makinschool.service;

import com.makin.makinschool.model.Course;
import com.makin.makinschool.model.Person;
import com.makin.makinschool.repository.CourseRepository;
import com.makin.makinschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourse(int courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.get();
    }

    public boolean addStudentToCourse(Person person, Course course) {
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (null != personEntity && personEntity.getPersonId() > 0) {
            personEntity.getCourses().add(course);
            course.getPersons().add(personEntity);
            personRepository.save(personEntity);
            return true;
        } else {
            return false;
        }
    }

    public void deleteStudentFromCourse(int studentId, Course course, HttpSession session) {
        Optional<Person> student = personRepository.findById(studentId);
        student.get().getCourses().remove(course);
        course.getPersons().remove(student);
        personRepository.save(student.get());
        session.setAttribute("course", course);
    }
}