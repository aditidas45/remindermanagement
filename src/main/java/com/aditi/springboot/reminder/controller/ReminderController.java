package com.aditi.springboot.reminder.controller;

import com.aditi.springboot.reminder.model.Reminder;
import com.aditi.springboot.reminder.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class ReminderController {

    private ReminderService service;

    @Autowired
    public ReminderController(ReminderService service) {
        this.service = service;
    }

    @GetMapping("/reminders")
    public String showAllReminders(Model model) {
        model.addAttribute("reminders", service.findAll());
        String name = getLoggedInUserName(model);
        model.addAttribute("name", name);
        return "reminders";
    }

    @GetMapping("/new-reminder")
    public String showReminderCreationForm(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "new-reminder";
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewReminder(@Valid @ModelAttribute Reminder reminder, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-reminder";
        }
        service.save(reminder);
        model.addAttribute("reminders", service.findAll());
        return "reminders";
    }

    @GetMapping("/{id}")
    public String showReminderdById(@PathVariable Long id, Model model) {
        Reminder reminder = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reminder Id:" + id));
        model.addAttribute("reminder", reminder);
        return "edit-reminder";
    }

    @PostMapping("/{id}/update")
    public String updateReminder(@PathVariable Long id, @Valid @ModelAttribute Reminder reminder, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-reminder";
        }
        service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reminder Id:" + id));
        service.save(reminder);
        model.addAttribute("reminders", service.findAll());
        return "reminders";
    }

    @PostMapping("/{id}/delete")
    public String deleteReminder(@PathVariable Long id, Model model) {
        service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reminder Id:" + id));
        service.deleteById(id);
        model.addAttribute("reminders", service.findAll());
        return "reminders";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        model.put("name", getLoggedinUserName());
        return "reminders";
    }

    private String getLoggedInUserName(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    private String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    authentication);
        }

        return "redirect:/";
    }
}
