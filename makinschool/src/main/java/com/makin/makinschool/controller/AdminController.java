package com.makin.makinschool.controller;

import com.makin.makinschool.model.Course;
import com.makin.makinschool.model.MakinClass;
import com.makin.makinschool.model.Person;
import com.makin.makinschool.service.CourseService;
import com.makin.makinschool.service.MakinClassService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private MakinClassService makinClassService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses() {
        List<MakinClass> makinClasses = makinClassService.getAllClasses();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("makinClasses", makinClasses);
        modelAndView.addObject("makinClass", new MakinClass());
        modelAndView.addObject("appName", "Makin School");
        modelAndView.addObject("currentPage", "displayClasses");
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(@ModelAttribute("makinClass") MakinClass makinClass) {
        makinClassService.addClass(makinClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int id) {
        makinClassService.deleteClass(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(@RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMsg = null;
        MakinClass makinClass = makinClassService.findClass(classId);
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("makinClass", makinClass);
        modelAndView.addObject("person", new Person());
        modelAndView.addObject("appName", "Makin School");
        session.setAttribute("makinClass", makinClass);
        if (null != error) {
            errorMsg = "Invalid email entered!";
            modelAndView.addObject("errorMessage", errorMsg);
        }
        return modelAndView;
    }

    @RequestMapping("/addStudent")
    public ModelAndView addStudent(@ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        MakinClass makinClass = (MakinClass) session.getAttribute("makinClass");
        boolean isSaved = makinClassService.addStudent(makinClass, person, session);
        if (isSaved) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + makinClass.getClassId());
        } else {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" +
                    makinClass.getClassId() + "&error=true");
        }
        return modelAndView;
    }

    @RequestMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam int personId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        MakinClass makinClass = (MakinClass) session.getAttribute("makinClass");
        makinClassService.deleteStudent(personId, makinClass, session);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + makinClass.getClassId());
        return modelAndView;
    }

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        List<Course> courses = courseService.getAllCourses();
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new Course());
        return modelAndView;
    }

    @RequestMapping("/addNewCourse")
    public ModelAndView addCourse(@ModelAttribute("course") Course course, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        courseService.addCourse(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @RequestMapping("/viewStudents")
    public ModelAndView viewStudents(@RequestParam int id, HttpSession session,
                                     @RequestParam(required = false) String error) {
        String errorMsg = null;
        ModelAndView modelAndView = new ModelAndView("course_students");
        Course course = courseService.getCourse(id);
        modelAndView.addObject("course", course);
        modelAndView.addObject("person", new Person());
        modelAndView.addObject("appName", "Makin School");
        session.setAttribute("course", course);
        if (null != error) {
            errorMsg = "Invalid email entered!";
            modelAndView.addObject("errorMessage", errorMsg);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(
            @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Course course = (Course) session.getAttribute("course");
        boolean isAdded = courseService.addStudentToCourse(person, course);
        if (isAdded) {
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+course.getCourseId());
            session.setAttribute("course", course);
        } else {
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+course.getCourseId() +
                    "&error=true");
        }
        return modelAndView;
    }
}
