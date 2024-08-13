package com.makin.makinschool.repository;

import com.makin.makinschool.model.HolidayModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<HolidayModel, String> {

}
