package com.makin.makinschool.controller;

import com.makin.makinschool.model.HolidayModel;
import com.makin.makinschool.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayPageController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/holidays/{display}")
    public String displayHolidaysPage(
            @PathVariable String display,
            Model model) {
        List<HolidayModel> holidays = holidayService.findAllHolidays();

        HolidayModel.Type[] types = HolidayModel.Type.values();
        for (HolidayModel.Type type: types) {
            model.addAttribute(
                    type.toString(),
                    (holidays.stream().filter(holidayModel -> holidayModel.getType().equals(type)).
                            collect(Collectors.toList())));
        }

        boolean festival = display.equals("all") || display.equals("festival");
        boolean federal = display.equals("all") || display.equals("federal");

        model.addAttribute("appName", "Makin School");
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        return "holidays";
    }
}
