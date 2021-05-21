package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.service.DictionaryService;
import com.selflearning.englishcourses.service.PhraseService;
import com.selflearning.englishcourses.service.SentenceService;
import com.selflearning.englishcourses.service.VocabularyService;
import com.selflearning.englishcourses.service.dto.DictionaryDto;
import com.selflearning.englishcourses.service.dto.PhraseDto;
import com.selflearning.englishcourses.service.dto.SentenceDto;
import com.selflearning.englishcourses.service.dto.VocabularyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private SentenceService sentenceService;

    private PhraseService phraseService;

    private VocabularyService vocabularyService;

    @Autowired
    public void setSentenceService(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @Autowired
    public void setVocabularyService(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @Autowired
    public void setPhraseService(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @Override
    public DictionaryDto searchAll(String keyword) {
        Pageable pageable = PageRequest.of(0, 20);
        Page<SentenceDto> sentenceDtoPage = this.searchSentence(keyword, pageable);
        Page<VocabularyDto> vocabularyDtos = this.searchVocabulary(keyword, pageable);
        Page<PhraseDto> phraseDtos = this.searchPhrase(keyword, pageable);
        DictionaryDto dictionaryDto = new DictionaryDto();
        dictionaryDto.setSentenceDtoPage(sentenceDtoPage);
        dictionaryDto.setVocabularyDtoPage(vocabularyDtos);
        dictionaryDto.setPhraseDtoPage(phraseDtos);
        return dictionaryDto;
    }

    @Override
    public Page<SentenceDto> searchSentence(String keyword, Pageable pageable) {
        return sentenceService.convertEntityPageToDtoPage(sentenceService.search(keyword, pageable));
    }

    @Override
    public Page<VocabularyDto> searchVocabulary(String keyword, Pageable pageable) {
        return vocabularyService.convertEntityPageToDtoPage(vocabularyService.search(keyword, pageable));
    }

    @Override
    public Page<PhraseDto> searchPhrase(String keyword, Pageable pageable) {
        return phraseService.convertEntityPageToDtoPage(phraseService.search(keyword, pageable));
    }


}
