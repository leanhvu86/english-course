package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.RegistrationToken;
import com.selflearning.englishcourses.domain.Role;
import com.selflearning.englishcourses.domain.User;
import com.selflearning.englishcourses.exception.ActivateAccountException;
import com.selflearning.englishcourses.repository.elasticsearch.RegistrationTokenElasticsearchRepository;
import com.selflearning.englishcourses.repository.elasticsearch.RoleElasticsearchRepository;
import com.selflearning.englishcourses.repository.elasticsearch.UserElasticsearchRepository;
import com.selflearning.englishcourses.repository.jpa.RegistrationTokenJpaRepository;
import com.selflearning.englishcourses.repository.jpa.RoleJpaRepository;
import com.selflearning.englishcourses.repository.jpa.UserJpaRepository;
import com.selflearning.englishcourses.service.MailService;
import com.selflearning.englishcourses.service.UserService;
import com.selflearning.englishcourses.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE = "USER";
    private UserJpaRepository userJpaRepository;
    private UserElasticsearchRepository userElasticsearchRepository;
    private RoleJpaRepository roleJpaRepository;
    private RoleElasticsearchRepository roleElasticsearchRepository;
    private RegistrationTokenJpaRepository registrationTokenJpaRepository;
    private RegistrationTokenElasticsearchRepository registerTokenElasticsearchRepository;
    private MailService mailService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Autowired
    public void setRegisterTokenElasticsearchRepository(RegistrationTokenElasticsearchRepository registerTokenElasticsearchRepository) {
        this.registerTokenElasticsearchRepository = registerTokenElasticsearchRepository;
    }

    @Autowired
    public void setRegistrationTokenJpaRepository(RegistrationTokenJpaRepository registrationTokenJpaRepository) {
        this.registrationTokenJpaRepository = registrationTokenJpaRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleElasticsearchRepository(RoleElasticsearchRepository roleElasticsearchRepository) {
        this.roleElasticsearchRepository = roleElasticsearchRepository;
    }

    @Autowired
    public void setRoleJpaRepository(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Autowired
    public void setUserElasticsearchRepository(UserElasticsearchRepository userElasticsearchRepository) {
        this.userElasticsearchRepository = userElasticsearchRepository;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public User get(UUID id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public User convertDtoToEntity(UserDto userDto) {
        return null;
    }

    @Override
    public List<User> convertDtoToEntity(List<UserDto> userDtos) {
        return null;
    }

    @Override
    public UserDto convertEntityToDto(User entity) {
        return null;
    }

    @Override
    public List<UserDto> convertEntityToDto(List<User> entityList) {
        return null;
    }

    @Override
    public Page<UserDto> convertEntityPageToDtoPage(Page<User> page) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void register(RegistrationToken registrationToken) {
        User user = registrationToken.getUser();
        setDefaultUserProperty(user);
        registrationTokenJpaRepository.save(registrationToken);
        mailService.sendMailActivateAccount(registrationToken);
    }

    @Override
    public void activateAccount(UUID token) {
        Optional<RegistrationToken> registrationTokenOptional = registrationTokenJpaRepository.findById(token);
        if (registrationTokenOptional.isPresent()) {
            RegistrationToken registrationToken = registrationTokenOptional.get();
            if(registrationToken.getActive()){
                throw new ActivateAccountException();
            }
            registrationToken.setActive(true);
            registrationToken.getUser().setEnabled(true);
            registrationTokenJpaRepository.save(registrationToken);
        } else {
            throw new ActivateAccountException();
        }
    }

    @Override
    public void updatePassword(String password, String username) {
        userJpaRepository.updatePassword(password, username);
    }


    private void setDefaultUserProperty(User user) {
        Optional<Role> optionalRole = roleJpaRepository.findByName(USER_ROLE);
        if (optionalRole.isPresent()) {
            user.setRoles(Collections.singletonList(optionalRole.get()));
        } else {
            Role role = new Role(USER_ROLE);
            roleJpaRepository.save(role);
            if (Objects.nonNull(role.getId())) {
                user.setRoles(Collections.singletonList(role));
            } else {
                throw new RuntimeException("Cannot create Role!");
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLocked(false);
        user.setEnabled(false);
    }

    @Override
    public long count() {
        return userJpaRepository.count();
    }
}
