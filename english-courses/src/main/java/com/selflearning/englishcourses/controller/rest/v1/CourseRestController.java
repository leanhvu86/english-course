package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Course;
import com.selflearning.englishcourses.service.CourseService;
import com.selflearning.englishcourses.service.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CourseRestController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Page<CourseDto>> findAll(Pageable pageable) {
        Page<Course> coursePage = courseService.findAll(pageable);
        return new ResponseEntity<>(courseService.convertEntityPageToDtoPage(coursePage), HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(courseService.convertEntityToDto(courseService.get(id)), HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto courseDto) {
        Course course = courseService.convertDtoToEntity(courseDto);
        courseService.save(course);
        return new ResponseEntity<>(courseService.convertEntityToDto(course), HttpStatus.CREATED);
    }

    @PostMapping("/courses/all")
    public ResponseEntity<List<CourseDto>> createCourses(@Valid @RequestBody List<CourseDto> courseDtos) {
        List<Course> courses = courseService.convertDtoToEntity(courseDtos);
        courseService.saveAll(courses);
        courseDtos = courseService.convertEntityToDto(courses);
        return new ResponseEntity<>(courseDtos, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("id") UUID id, @Valid @RequestBody CourseDto courseDto) {
        Course currentCourse = courseService.get(id);
        currentCourse.setName(courseDto.getName());
        currentCourse.setDescription(courseDto.getDescription());
        courseService.save(currentCourse);
        return new ResponseEntity<>(courseService.convertEntityToDto(currentCourse), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") UUID id) {
        Course currentCourse = courseService.get(id);
        courseService.delete(currentCourse);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
