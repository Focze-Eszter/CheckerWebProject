package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.User;
import ro.siit.airports.repository.UserRepository;
import ro.siit.airports.service.UserService;

import java.util.Objects;

@Controller
public class UserController {

   /* @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repo;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/process_register")
    public String processRegistration ( User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repo.save(user);
            return "login-successfully";

    }

   */

}
