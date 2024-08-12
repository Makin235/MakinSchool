package com.makin.makinschool.service;

import com.makin.makinschool.MakinSchoolConstants;
import com.makin.makinschool.model.ContactModel;
import com.makin.makinschool.model.HolidayModel;
import com.makin.makinschool.repository.ContactRepository;
import com.makin.makinschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    /**
     * Get Holidays from DB
     * @return List<HolidayModel>
     */
    public List<HolidayModel> findAllHolidays() {
        List<HolidayModel> holidays = holidayRepository.findAllHolidays();
        return holidays;
    }
}
