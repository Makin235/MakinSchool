package com.makin.makinschool.service;

import com.makin.makinschool.model.HolidayModel;
import com.makin.makinschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    /**
     * Get Holidays from DB
     * @return List<HolidayModel>
     */
    public List<HolidayModel> findAllHolidays() {
        Iterable<HolidayModel> holidays = holidayRepository.findAll();
        List<HolidayModel> holidaysList = StreamSupport.stream(holidays.spliterator(), false).collect(Collectors.toList());
        return holidaysList;
    }
}
