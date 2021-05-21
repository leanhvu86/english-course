package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDto extends BaseDto {

    private String username;
    private String email;
    private Boolean gender;

}
