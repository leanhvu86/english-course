package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/count")
    public ResponseEntity<Long> getTotalUsers(){
        return new ResponseEntity<>(userService.count(), HttpStatus.OK);
    }

}
