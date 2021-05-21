package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.Vocabulary;
import com.selflearning.englishcourses.domain.Word;
import com.selflearning.englishcourses.repository.elasticsearch.VocabularyElasticsearchRepository;
import com.selflearning.englishcourses.repository.elasticsearch.WordElasticsearchRepository;
import com.selflearning.englishcourses.repository.jpa.WordJpaRepository;
import com.selflearning.englishcourses.service.WordService;
import com.selflearning.englishcourses.service.dto.WordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WordServiceImpl implements WordService {

    private WordJpaRepository wordJpaRepository;

    private WordElasticsearchRepository wordElasticsearchRepository;

    @Autowired
    private VocabularyElasticsearchRepository vocabularyElasticsearchRepository;

    @Autowired
    public void setWordJpaRepository(WordJpaRepository wordJpaRepository) {
        this.wordJpaRepository = wordJpaRepository;
    }

    @Autowired
    public void setVocabularyElasticsearchRepository(VocabularyElasticsearchRepository vocabularyElasticsearchRepository) {
        this.vocabularyElasticsearchRepository = vocabularyElasticsearchRepository;
    }

    @Override
    public Word get(UUID id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(Word obj) {
        wordJpaRepository.save(obj);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Word obj) {
        wordJpaRepository.delete(obj);
    }

    @Override
    public Page<Word> findAll(Pageable pageable) {
        return wordJpaRepository.findAll(pageable);
    }

    @Override
    public Word convertDtoToEntity(WordDto wordDto) {
        return null;
    }

    @Override
    public List<Word> convertDtoToEntity(List<WordDto> wordDtos) {
        return null;
    }

    @Override
    public WordDto convertEntityToDto(Word entity) {
        return null;
    }

    @Override
    public List<WordDto> convertEntityToDto(List<Word> entityList) {
        return null;
    }

    @Override
    public Page<WordDto> convertEntityPageToDtoPage(Page<Word> page) {
        return new PageImpl<>(convertEntityToDto(page.getContent()), page.getPageable(), page.getTotalElements());
    }

}
