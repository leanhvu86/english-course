package com.selflearning.englishcourses.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class ModuleDto extends BaseDto {

    @NotEmpty(message = "error.module.name.empty")
    @Length(max = 255, message = "error.module.name.length")
    private String name;

}
