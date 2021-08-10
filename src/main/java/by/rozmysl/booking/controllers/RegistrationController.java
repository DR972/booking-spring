package by.rozmysl.booking.controllers;

import by.rozmysl.booking.entity.user.MyUser;
import by.rozmysl.booking.service.usersService.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
/**
 * The class processes registration requests, creates the appropriate model and passes it for display
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    /**
     * The method makes a get request and displays a form on the page for creating a new user
     * @param model  the following attributes have been added: a form for creating a new user
     * @return  the registration page
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new MyUser());
        return "registration";
    }

    /**
     * The method makes the following changes to the user database: creates a new user, and also checks the correctness of the entered data
     * @param userForm  user form
     * @param bindingResult  binding result in the user form
     * @param model  the following attributes have been added: a password error, a username error
     * @return  redirect the login page
     */
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid MyUser userForm, BindingResult bindingResult, Model model) {
        if (userForm == null) return "redirect:/registration";
        if (bindingResult.hasErrors()) return "registration";
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "passwordError");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "usernameError");
            return "registration";
        }
        return "redirect:/login";
    }

    /**
     * The method makes a get request and displays the results of user activation on the page
     * @param model  the following attributes have been added: the message with the activation result
     * @param code  activation code
     * @return  the login page
     */
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable("code") String code) {
        if (userService.activateUser(code)) model.addAttribute("message", "activeTrue");
        else model.addAttribute("message", "activeFalse");
        return "login";
    }
}