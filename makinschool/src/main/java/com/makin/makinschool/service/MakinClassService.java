package com.makin.makinschool.service;

import com.makin.makinschool.model.MakinClass;
import com.makin.makinschool.model.Person;
import com.makin.makinschool.repository.MakinClassRepository;
import com.makin.makinschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MakinClassService {

    @Autowired
    private MakinClassRepository makinClassRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<MakinClass> getAllClasses() {
        List<MakinClass> makinClasses = makinClassRepository.findAll();
        return makinClasses;
    }

    public void addClass(MakinClass makinClass) {
        makinClassRepository.save(makinClass);
    }

    public void deleteClass(int classId) {
        Optional<MakinClass> makinClass = makinClassRepository.findById(classId);
        for(Person person : makinClass.get().getPersons()){
            person.setMakinClass(null);
            personRepository.save(person);
        }
        makinClassRepository.deleteById(classId);
    }

    public MakinClass findClass(int classId) {
        Optional<MakinClass> makinClass = makinClassRepository.findById(classId);
        return makinClass.get();
    }

    public boolean addStudent(MakinClass makinClass, Person person, HttpSession session) {
        boolean isAdded = false;
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (null != personEntity && personEntity.getPersonId() > 0) {
            personEntity.setMakinClass(makinClass);
            personRepository.save(personEntity);
            makinClass.getPersons().add(personEntity);
            makinClassRepository.save(makinClass);
            isAdded = true;
        }
        return isAdded;
    }
    public void deleteStudent(int studentId, MakinClass makinClass, HttpSession session) {
        Optional<Person> person = personRepository.findById(studentId);
        person.get().setMakinClass(null);
        makinClass.getPersons().remove(person.get());
        MakinClass savedMakinClass = makinClassRepository.save(makinClass);
        session.setAttribute("makinClass", savedMakinClass);
    }
}
