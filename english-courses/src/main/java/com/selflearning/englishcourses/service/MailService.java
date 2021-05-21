package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.RegistrationToken;

public interface MailService {

    void sendMailActivateAccount(RegistrationToken registerToken);

}
