package com.makin.makinschool.repository;

import com.makin.makinschool.model.ContactModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Integer> {

    List<ContactModel> findByStatus(String status);

    //@Query("SELECT c FROM ContactModel c WHERE c.status = :status") //JPQL
    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true) //Native SQL
    Page<ContactModel> findByStatus(String status, Pageable pageable);
}
