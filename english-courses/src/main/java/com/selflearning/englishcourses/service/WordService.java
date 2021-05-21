package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Word;
import com.selflearning.englishcourses.service.dto.WordDto;

import java.util.UUID;

public interface WordService extends BaseCurdService<Word, UUID>, ModelMapperService<Word, WordDto> {

}
