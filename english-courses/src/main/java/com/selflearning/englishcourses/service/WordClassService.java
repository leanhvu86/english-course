package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.WordClass;
import com.selflearning.englishcourses.service.dto.WordClassDto;

import java.util.UUID;

public interface WordClassService extends BaseCurdService<WordClass, UUID>, ModelMapperService<WordClass, WordClassDto> {
}
