package com.selflearning.englishcourses.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PhraseDetailDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PhraseDto phrase;
    private String meaning;
    private String description;
    private String synonyms;
    private String note;

}
