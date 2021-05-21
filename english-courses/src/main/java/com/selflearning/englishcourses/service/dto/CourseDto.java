package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CourseDto extends BaseDto{

    @NotEmpty(message = "error.course.name.empty")
    @Length(message = "error.course.name.length")
    private String name;

    @Length(message = "error.course.description.length")
    private String description;

}
