package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.domain.Lesson;
import com.selflearning.englishcourses.domain.LessonModule;
import com.selflearning.englishcourses.domain.Module;
import com.selflearning.englishcourses.repository.jpa.CourseJpaRepository;
import com.selflearning.englishcourses.repository.jpa.LessonModuleJpaRepository;
import com.selflearning.englishcourses.service.CourseService;
import com.selflearning.englishcourses.service.LessonModuleService;
import com.selflearning.englishcourses.service.LessonService;
import com.selflearning.englishcourses.service.ModuleService;
import com.selflearning.englishcourses.service.dto.LessonModuleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonModuleServiceImpl implements LessonModuleService {

    private LessonModuleJpaRepository lessonModuleJpaRepository;

    private LessonService lessonService;

    private ModuleService moduleService;

    private ModelMapper modelMapper;

    @Autowired
    public void setLessonModuleJpaRepository(LessonModuleJpaRepository lessonModuleJpaRepository) {
        this.lessonModuleJpaRepository = lessonModuleJpaRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Override
    public LessonModule get(UUID id) {
        return lessonModuleJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(LessonModule lessonModule) {
        lessonModuleJpaRepository.save(lessonModule);
    }

    @Override
    public Page<LessonModule> findAll(Pageable pageable) {
        return lessonModuleJpaRepository.findAll(pageable);
    }

    @Override
    public LessonModule convertDtoToEntity(LessonModuleDto lessonModuleDto) {
        LessonModule lessonModule = modelMapper.map(lessonModuleDto, LessonModule.class);
        UUID lessonId = lessonModuleDto.getLessonId();
        if (Objects.nonNull(lessonId)) {
            Lesson lesson = lessonService.get(lessonId);
            lessonModule.setLesson(lesson);
        }
        UUID moduleId = lessonModuleDto.getModuleId();
        if (Objects.nonNull(moduleId)) {
            Module module = moduleService.get(moduleId);
            lessonModule.setModule(module);
        }
        return lessonModule;
    }

    @Override
    public List<LessonModule> convertDtoToEntity(List<LessonModuleDto> lessonModuleDtos) {
        return lessonModuleDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    @Override
    public LessonModuleDto convertEntityToDto(LessonModule lessonModule) {
        LessonModuleDto lessonModuleDto = modelMapper.map(lessonModule, LessonModuleDto.class);
        lessonModuleDto.setLessonId(lessonModule.getLesson().getId());
        lessonModuleDto.setCourseId(lessonModule.getLesson().getCourse().getId());
        lessonModuleDto.setOrderNumber(lessonModule.getLesson().getOrderNumber());
        lessonModuleDto.setModuleId(lessonModule.getModule().getId());
        lessonModuleDto.setModuleName(lessonModule.getModule().getName());
        return lessonModuleDto;
    }

    @Override
    public List<LessonModuleDto> convertEntityToDto(List<LessonModule> lessonModules) {
        return lessonModules.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Page<LessonModuleDto> convertEntityPageToDtoPage(Page<LessonModule> lessonModulePage) {
        return new PageImpl<>(convertEntityToDto(lessonModulePage.getContent()),
                lessonModulePage.getPageable(), lessonModulePage.getTotalElements());
    }
}
