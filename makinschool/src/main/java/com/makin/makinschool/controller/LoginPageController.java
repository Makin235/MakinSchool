package com.makin.makinschool.controller;

import com.makin.makinschool.config.MakinSchoolProps;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginPageController {

    @Autowired
    private Environment environment;

    @Autowired
    private MakinSchoolProps makinSchoolProps;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "register", required = false) String register,
            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        } else if (logout != null) {
            if (null != makinSchoolProps.getMsg()
                    && null != makinSchoolProps.getMsg().get("logout")) {
                errorMessge = makinSchoolProps.getMsg().get("logout");
            }
            //errorMessge = environment.getProperty("makinschool.msg.logout");
        } else if (register != null) {
            errorMessge = "You have been successfully registered !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        model.addAttribute("appName", "Makin School");
        model.addAttribute("currentPage", "login");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("appName", "Makin School");
        log.info(makinSchoolProps.getBranches().get(1));
        return "redirect:/login?logout=true";
    }
}
