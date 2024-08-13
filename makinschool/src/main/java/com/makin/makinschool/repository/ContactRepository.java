package com.makin.makinschool.repository;

import com.makin.makinschool.model.ContactModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<ContactModel, Integer> {

    List<ContactModel> findByStatus(String status);
}
