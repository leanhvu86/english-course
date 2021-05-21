package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class Registration {

    @NotEmpty(message = "registration.errors.username.empty")
    @Length(min = 5, max = 45, message = "registration.errors.username.length")
    private String username;

    @NotEmpty(message = "registration.errors.email.empty")
    @Length(min = 6, max = 80, message = "registration.errors.email.length")
    @Email(message = "registration.errors.email.email")
    private String email;

    @NotEmpty(message = "registration.errors.password.empty")
    @Length(min = 6, max = 32, message = "registration.errors.password.length")
    private String password;

    @NotEmpty(message = "registration.errors.confirmPassword.empty")
    @Length(min = 6, max = 32, message = "registration.errors.confirmPassword.length")
    private String confirmPassword;

    @NotEmpty(message = "registration.errors.firstName.empty")
    @Length(max = 45, message = "registration.errors.firstName.length")
    private String firstName;

    @NotEmpty(message = "registration.errors.lastName.empty")
    @Length(max = 45, message = "registration.errors.lastName.length")
    private String lastName;

    @NotNull(message = "registration.errors.gender.null")
    private Boolean gender;

}
