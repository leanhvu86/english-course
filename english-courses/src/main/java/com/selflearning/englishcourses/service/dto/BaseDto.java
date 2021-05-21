package com.selflearning.englishcourses.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BaseDto {

    private UUID id;
    private Date createdTime;
    private Date updatedTime;

}
