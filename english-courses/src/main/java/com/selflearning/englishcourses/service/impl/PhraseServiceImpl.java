package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.Phrase;
import com.selflearning.englishcourses.domain.PhraseDetail;
import com.selflearning.englishcourses.repository.elasticsearch.PhraseElasticsearchRepository;
import com.selflearning.englishcourses.repository.jpa.PhraseJpaRepository;
import com.selflearning.englishcourses.service.PhraseDetailService;
import com.selflearning.englishcourses.service.PhraseService;
import com.selflearning.englishcourses.service.dto.PhraseDetailDto;
import com.selflearning.englishcourses.service.dto.PhraseDto;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class PhraseServiceImpl implements PhraseService {

    private PhraseJpaRepository phraseJpaRepository;

    private PhraseElasticsearchRepository phraseElasticsearchRepository;

    private PhraseDetailService phraseDetailService;

    private ModelMapper modelMapper;

    @Autowired
    public void setPhraseJpaRepository(PhraseJpaRepository phraseJpaRepository) {
        this.phraseJpaRepository = phraseJpaRepository;
    }

    @Autowired
    public void setPhraseElasticsearchRepository(PhraseElasticsearchRepository phraseElasticsearchRepository) {
        this.phraseElasticsearchRepository = phraseElasticsearchRepository;
    }

    @Autowired
    public void setPhraseDetailService(PhraseDetailService phraseDetailService) {
        this.phraseDetailService = phraseDetailService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Phrase get(UUID id) {
        return phraseJpaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Phrase phrase) {
        phraseJpaRepository.save(phrase);
        phraseElasticsearchRepository.save(phrase);
    }

    @Override
    public void saveAll(List<Phrase> phrases) {
        phraseJpaRepository.saveAll(phrases);
        phraseElasticsearchRepository.saveAll(phrases);
    }

    @Override
    public void delete(Phrase phrase) {
        phraseJpaRepository.delete(phrase);
        phraseElasticsearchRepository.delete(phrase);
    }

    @Override
    public void deleteAll(List<Phrase> phrases) {
        phraseJpaRepository.deleteAll(phrases);
        phraseElasticsearchRepository.deleteAll(phrases);
    }

    @Override
    public Page<Phrase> findAll(Pageable pageable) {
        return phraseJpaRepository.findAll(pageable);
    }

    @Override
    public Page<Phrase> search(String value, Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(value, "text", "phraseDetails.meaning", "phraseDetails.description"))
                .withPageable(pageable)
                .build();
        return phraseElasticsearchRepository.search(searchQuery);
    }

    @Override
    public Phrase convertDtoToEntity(PhraseDto phraseDto) {
        Phrase phrase = modelMapper.map(phraseDto, Phrase.class);
        List<PhraseDetailDto> phraseDetails = phraseDto.getPhraseDetails();
        if (Objects.nonNull(phraseDetails) && !phraseDetails.isEmpty()) {
            phrase.setPhraseDetails(phraseDetailService.convertDtoToEntity(phraseDetails));
        }
        return phrase;
    }

    @Override
    public List<Phrase> convertDtoToEntity(List<PhraseDto> phraseDtos) {
        return phraseDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    @Override
    public PhraseDto convertEntityToDto(Phrase phrase) {
        return modelMapper.map(phrase, PhraseDto.class);

    }

    @Override
    public List<PhraseDto> convertEntityToDto(List<Phrase> phrases) {
        return phrases.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Page<PhraseDto> convertEntityPageToDtoPage(Page<Phrase> phrasePage) {
        return new PageImpl<>(convertEntityToDto(phrasePage.getContent()), phrasePage.getPageable(), phrasePage.getTotalElements());
    }


    @Override
    public long count() {
        return phraseJpaRepository.count();
    }
}
