package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PhraseDto extends BaseDto {

    private String text;
    private String ipa;
    private List<PhraseDetailDto> phraseDetails;

}
