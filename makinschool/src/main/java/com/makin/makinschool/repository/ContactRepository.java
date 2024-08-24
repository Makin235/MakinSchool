package com.makin.makinschool.repository;

import com.makin.makinschool.model.ContactModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Integer> {

    List<ContactModel> findByStatus(String status);

    //@Query("SELECT c FROM ContactModel c WHERE c.status = :status") //JPQL
    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true) //Native SQL
    Page<ContactModel> findByStatus(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE ContactModel c SET c.status = ?1 WHERE c.contactId =?2")
    int updateStatusById(String status, int id);
}
