package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.RegistrationToken;
import com.selflearning.englishcourses.domain.User;
import com.selflearning.englishcourses.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends BaseService, BaseCurdService<User, UUID>, ModelMapperService<User, UserDto>, UserDetailsService {

    void register(RegistrationToken registrationToken);

    void activateAccount(UUID token);

    void updatePassword(String password, String username);

}
