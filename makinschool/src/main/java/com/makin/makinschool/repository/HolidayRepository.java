package com.makin.makinschool.repository;

import com.makin.makinschool.model.HolidayModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false) //this repository is not exposed as rest resource
public interface HolidayRepository extends CrudRepository<HolidayModel, String> {

}
