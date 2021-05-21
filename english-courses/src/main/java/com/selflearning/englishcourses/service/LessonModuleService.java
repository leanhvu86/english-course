package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.LessonModule;
import com.selflearning.englishcourses.service.dto.LessonModuleDto;

import java.util.UUID;

public interface LessonModuleService extends BaseCurdService<LessonModule, UUID>, ModelMapperService<LessonModule, LessonModuleDto> {


}
