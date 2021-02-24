package com.marcinha.stylist.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import com.marcinha.stylist.entity.CrmUser;
import com.marcinha.stylist.entity.User;
import com.marcinha.stylist.service.NotificationService;
import com.marcinha.stylist.service.UserService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/registry")
public class RegistrationController {

    private UserService service;
    private NotificationService notificationService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public RegistrationController(UserService service, NotificationService notificationService) {
        this.service = service;
        this.notificationService = notificationService;

    }

    @GetMapping("/showRegistrationForm")
    public String showRegistration(Model model) {

        model.addAttribute("crmUser", new CrmUser());

        return "registration-form";
    }


    @PostMapping("/processingRegistrationForm")
    public String registry(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
                           BindingResult theBindingResult,
                           Model theModel) {
        String username = theCrmUser.getUserName();
        logger.info("Processing registration form for: " + username);

        if (theBindingResult.hasErrors()) {
            logger.info("binding error");

            System.out.println(theBindingResult.getAllErrors());
            return "registration-form";
        }

        User existingUser = service.findByUserName(username);
        if (existingUser != null) {
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User already exist in database");

            logger.warning("User name already exists.");


            return "registration-form";
        }

        service.save(theCrmUser);

        logger.info("Successfully created user: " + username);


//        //method for email
//        try {
//            notificationService.sendNotification(theCrmUser);
//        }catch (MailException e){
//            logger.info("email error");
//            e.printStackTrace();
//        }


        logger.info("Everything done");
        return "registration-confirmation";
    }

}
