package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Phrase;
import com.selflearning.englishcourses.service.dto.PhraseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PhraseService extends BaseService, BaseCurdService<Phrase, UUID>, ModelMapperService<Phrase, PhraseDto> {

    Page<Phrase> search(String value, Pageable pageable);

}
