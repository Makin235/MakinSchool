package com.makin.makinschool.repository;

import com.makin.makinschool.model.MakinClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakinClassRepository extends JpaRepository<MakinClass, Integer> {
}
