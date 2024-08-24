package com.makin.makinschool.repository;

import com.makin.makinschool.model.ContactModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Integer> {

    List<ContactModel> findByStatus(String status);
    Page<ContactModel> findByStatus(String status, Pageable pageable);
}
