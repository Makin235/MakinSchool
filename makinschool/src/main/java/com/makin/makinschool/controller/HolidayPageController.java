package com.makin.makinschool.controller;

import com.makin.makinschool.model.HolidayModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayPageController {

    @RequestMapping("/holidays")
    public String displayHolidaysPage(Model model) {
        List<HolidayModel> holidays = Arrays.asList(
                new HolidayModel("July 4", "Independence Day", HolidayModel.Type.FEDERAL),
                new HolidayModel("Sep 5", "Labor Day", HolidayModel.Type.FEDERAL),
                new HolidayModel("Oct 31", "Halloween", HolidayModel.Type.FESTIVAL)
        );

        HolidayModel.Type[] types = HolidayModel.Type.values();
        for (HolidayModel.Type type: types) {
            model.addAttribute(
                    type.toString(),
                    (holidays.stream().filter(holidayModel -> holidayModel.getType().equals(type)).
                            collect(Collectors.toList())));
        }

        model.addAttribute("appName", "Makin School");
        return "holidays";
    }
}
