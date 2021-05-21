package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.repository.jpa.CourseJpaRepository;
import com.selflearning.englishcourses.service.CourseService;
import com.selflearning.englishcourses.service.dto.CourseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseJpaRepository courseJpaRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setCourseJpaRepository(CourseJpaRepository courseJpaRepository) {
        this.courseJpaRepository = courseJpaRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Course get(UUID id) {
        return courseJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Course course) {
        courseJpaRepository.save(course);
    }

    @Transactional
    @Override
    public void saveAll(List<Course> courses) {
        courseJpaRepository.saveAll(courses);
    }

    @Override
    public void delete(Course course) {
        courseJpaRepository.delete(course);
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return courseJpaRepository.findAll(pageable);
    }

    @Override
    public Course convertDtoToEntity(CourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }

    @Override
    public List<Course> convertDtoToEntity(List<CourseDto> courseDtos) {
        return courseDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    @Override
    public CourseDto convertEntityToDto(Course entity) {
        return modelMapper.map(entity, CourseDto.class);
    }

    @Override
    public List<CourseDto> convertEntityToDto(List<Course> courses) {
        return courses.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Page<CourseDto> convertEntityPageToDtoPage(Page<Course> page) {
        return new PageImpl<>(convertEntityToDto(page.getContent()), page.getPageable(), page.getNumberOfElements());
    }


}
