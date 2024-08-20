package com.makin.makinschool.service;

import com.makin.makinschool.constant.MakinSchoolConstants;
import com.makin.makinschool.model.Address;
import com.makin.makinschool.model.Person;
import com.makin.makinschool.model.Profile;
import com.makin.makinschool.model.Roles;
import com.makin.makinschool.repository.PersonRepository;
import com.makin.makinschool.repository.RolesRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(MakinSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }

    public Person getPersonByEmail(String email) {
        Person person = personRepository.readByEmail(email);
        return person;
    }

    public Profile getProfile(Person person) {
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());
        if (null != person.getAddress() && person.getAddress().getAddressId() > 0) {
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }

        return  profile;
    }

    public void setPerson(Profile profile, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if (null == person.getAddress() || person.getAddress().getAddressId() <= 0) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        if (!profile.getAddress2().isBlank()) {
            person.getAddress().setAddress2(profile.getAddress2());
        }
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        Person savedPerson = personRepository.save(person);
        session.setAttribute("loggedInPerson", savedPerson);
    }
}
