package com.selflearning.englishcourses.controller.web;

import com.selflearning.englishcourses.domain.RegistrationToken;
import com.selflearning.englishcourses.domain.User;
import com.selflearning.englishcourses.service.UserService;
import com.selflearning.englishcourses.service.dto.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<User> createUser(@Valid @RequestBody Registration registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setPassword(registration.getPassword());
        user.setEmail(registration.getEmail());
        user.setGender(registration.getGender());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setUser(user);
        registrationToken.setActive(false);
        userService.register(registrationToken);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/activate-account", params = "registration-token")
    @ResponseBody
    public ResponseEntity<User> activateUser(@RequestParam("registration-token") String token) {
        UUID uuid = UUID.fromString(token);
        userService.activateAccount(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
