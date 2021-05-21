package com.selflearning.englishcourses.service.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@ToString
public class SentenceDto  extends BaseDto{

    @NotEmpty(message = "errors.sentence.text.empty")
    @Length(max = 255, message = "errors.sentence.text.length")
    private String text;

    @Length(max = 255, message = "errors.sentence.ipa.length")
    private String ipa;

    @NotEmpty(message = "errors.sentence.meaning.empty")
    @Length(max = 255, message = "errors.sentence.meaning.length")
    private String meaning;

    @Length(max = 255, message = "errors.sentence.audioPath.length")
    private String audioPath;

}
