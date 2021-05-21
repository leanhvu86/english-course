package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Sentence;
import com.selflearning.englishcourses.service.dto.SentenceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SentenceService extends BaseService, BaseCurdService<Sentence, UUID>, ModelMapperService<Sentence, SentenceDto> {

    Page<Sentence> search(String value, Pageable pageable);

}
