package com.makin.makinschool.controller;

import com.makin.makinschool.model.HolidayModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayPageController {

    //@GetMapping("/holidays")
    @GetMapping("/holidays/{display}")
    public String displayHolidaysPage(
            //@RequestParam(required = false) boolean festival,
            //@RequestParam(required = false) boolean federal,
            @PathVariable String display,
            Model model) {
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

        boolean festival = display.equals("all") || display.equals("festival");
        boolean federal = display.equals("all") || display.equals("federal");

        model.addAttribute("appName", "Makin School");
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        return "holidays";
    }
}
