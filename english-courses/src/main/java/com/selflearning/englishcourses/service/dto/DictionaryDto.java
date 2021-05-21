package com.selflearning.englishcourses.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class DictionaryDto {

    private Page<VocabularyDto> vocabularyDtoPage;
    private Page<PhraseDto> phraseDtoPage;
    private Page<SentenceDto> sentenceDtoPage;

}
