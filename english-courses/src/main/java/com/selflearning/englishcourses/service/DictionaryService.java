package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.service.dto.DictionaryDto;
import com.selflearning.englishcourses.service.dto.PhraseDto;
import com.selflearning.englishcourses.service.dto.SentenceDto;
import com.selflearning.englishcourses.service.dto.VocabularyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DictionaryService {

    DictionaryDto searchAll(String keyword);

    Page<SentenceDto> searchSentence(String keyword, Pageable pageable);

    Page<VocabularyDto> searchVocabulary(String keyword, Pageable pageable);

    Page<PhraseDto> searchPhrase(String keyword, Pageable pageable);

}
