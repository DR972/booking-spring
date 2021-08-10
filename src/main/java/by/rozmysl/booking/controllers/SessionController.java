package by.rozmysl.booking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * The class processes session requests creates the appropriate model and passes it for display
 */
@Controller
public class SessionController {
    /**
     * The method makes a get request and displays the start page
     * @param model  the following attributes have been added: a new user session
     * @param session  user session
     * @return  the index page
     */
    @GetMapping("/")
    public String getSession(Model model, HttpSession session) {
        model.addAttribute("userSession", session.getAttribute("userSession"));
        return "index";
    }

    /**
     * The method makes a get request and displays the login form on the page, and also checks the correctness of the entered data
     * @param request  last request
     * @param error  login error
     * @param model  the following attributes have been added: a form for creating a new user session
     * @return  the login page
     */
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, @RequestParam(value = "error", defaultValue = "false") boolean error, Model model) {
        if (error) {
            model.addAttribute("error", ((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage());
            return "login";
        }
        return "login";
    }

    /**
     * The method makes the following changes to the session database: creates a new user session
     * @param userSession  user session
     * @param request  last request
     * @return  redirect the previous page
     */
    @PostMapping("/messages")
    public String saveSession(@RequestParam("userSession") String userSession, HttpServletRequest request) {
        request.getSession().setAttribute("userSession", userSession);
        return "redirect:/";
    }
}