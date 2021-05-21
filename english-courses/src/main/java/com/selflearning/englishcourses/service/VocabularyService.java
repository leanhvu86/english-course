package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Vocabulary;
import com.selflearning.englishcourses.service.dto.VocabularyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VocabularyService extends BaseService, BaseCurdService<Vocabulary, UUID>, ModelMapperService<Vocabulary, VocabularyDto> {

    Page<Vocabulary> search(String value, Pageable pageable);

}
