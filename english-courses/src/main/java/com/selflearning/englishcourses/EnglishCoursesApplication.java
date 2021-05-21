package com.selflearning.englishcourses;

import com.selflearning.englishcourses.domain.RegistrationToken;
import com.selflearning.englishcourses.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnglishCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishCoursesApplication.class, args);
    }

}
