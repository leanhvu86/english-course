package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WordClassDto extends BaseDto{

    private String name;
    private String meaning;
    private String description;

}
