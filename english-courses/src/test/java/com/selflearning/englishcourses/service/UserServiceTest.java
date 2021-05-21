package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.RegistrationToken;
import com.selflearning.englishcourses.domain.Role;
import com.selflearning.englishcourses.domain.User;
import com.selflearning.englishcourses.repository.jpa.RegistrationTokenJpaRepository;
import com.selflearning.englishcourses.repository.jpa.RoleJpaRepository;
import com.selflearning.englishcourses.repository.jpa.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationTokenJpaRepository registrationTokenJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void createRole() {
        Optional<Role> admin = roleJpaRepository.findByName("ADMIN");
        if(!admin.isPresent()){
            roleJpaRepository.save(new Role("ADMIN"));
        }
        Optional<Role> user = roleJpaRepository.findByName("USER");
        if(!user.isPresent()){
            roleJpaRepository.save(new Role("USER"));
        }
    }

    @Test
    public void deleteRole(){
        Optional<Role> admin = roleJpaRepository.findByName("ADMIN");
        if(admin.isPresent()){
            roleJpaRepository.delete(admin.get());
        }
        Optional<Role> user = roleJpaRepository.findByName("USER");
        if(user.isPresent()){
            roleJpaRepository.delete(user.get());
        }
    }



    @Test
    public void testSaveAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("manhnd.695@gmail.com");
        user.setEnabled(true);
        user.setFirstName("Mạnh");
        user.setPassword(passwordEncoder.encode("123"));
        user.setLastName("Nguyễn Đức");
        user.setLocked(false);
        user.setGender(true);
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setActive(true);
        registrationToken.setUser(user);
        Role adminRole = roleJpaRepository.findByName("ADMIN").get();
        Role userRole = roleJpaRepository.findByName("USER").get();
        user.setRoles(Arrays.asList(adminRole, userRole));
        registrationTokenJpaRepository.save(registrationToken);
    }

    @Test
    public void testSetAdminRole(){
        Optional<User> admin = userJpaRepository.findByUsernameOrEmail("admin");
        User user = admin.get();
        Role adminRole = roleJpaRepository.findByName("ADMIN").get();
        user.setRoles(Arrays.asList(adminRole));
        userJpaRepository.save(user);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("manhnd");
        user.setEmail("manhnd2261995@gmail.com");
        user.setEnabled(true);
        user.setFirstName("Mạnh");
        user.setPassword(passwordEncoder.encode("123"));
        user.setLastName("Nguyễn Đức");
        user.setLocked(false);
        user.setGender(true);
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setActive(true);
        registrationToken.setUser(user);
        Role userRole = roleJpaRepository.findByName("USER").get();
        user.setRoles(Arrays.asList(userRole));
        registrationTokenJpaRepository.save(registrationToken);
    }


    @Test
    public void testUpdateUser() {
        userService.updatePassword(passwordEncoder.encode("123"), "admin");
        userService.updatePassword(passwordEncoder.encode("123"), "manhnd");
    }

    @Test
    public void testDeleteUser() {

    }

}
