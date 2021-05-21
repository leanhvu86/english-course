package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Lesson;
import com.selflearning.englishcourses.service.LessonService;
import com.selflearning.englishcourses.service.dto.LessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author manhnd
 * @since 15/4/2019
 */
@RestController
@RequestMapping("/api/v1")
public class LessonRestController {

    private LessonService lessonService;

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/lessons")
    public ResponseEntity<Page<LessonDto>> getLessonPage(Pageable pageable){
        Page<Lesson> lessonPage = lessonService.findAll(pageable);
        return new ResponseEntity<>(lessonService.convertEntityPageToDtoPage(lessonPage), HttpStatus.OK);
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> getLessonPage(@PathVariable("id") UUID id){
        Lesson lesson = lessonService.get(id);
        return new ResponseEntity<>(lessonService.convertEntityToDto(lesson), HttpStatus.OK);
    }

    @GetMapping(value = "/lessons", params = "courseId")
    public ResponseEntity<Page<LessonDto>> getLessonByCourse(@RequestParam("courseId") UUID courseId, Pageable pageable){
        Page<Lesson> lessonPage = lessonService.findByCourseId(courseId, pageable);
        return new ResponseEntity<>(lessonService.convertEntityPageToDtoPage(lessonPage), HttpStatus.OK);
    }

    @PostMapping("/lessons")
    public ResponseEntity<LessonDto> createLesson(@Valid @RequestBody LessonDto lessonDto){
        Lesson lesson = lessonService.convertDtoToEntity(lessonDto);
        Optional<Lesson> lessonOptional = lessonService.findByLessonOrderNumberAndCourseId(lesson.getOrderNumber(), lesson.getCourse().getId());
        if(lessonOptional.isPresent()){
            HttpHeaders headers = new HttpHeaders();
            headers.add("entity-exists", "Lesson");
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        lessonService.save(lesson);
        return new ResponseEntity<>(lessonService.convertEntityToDto(lesson), HttpStatus.CREATED);
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("id") UUID id, @Valid @RequestBody LessonDto lessonDto){
        Lesson newLesson = lessonService.convertDtoToEntity(lessonDto);
        Lesson lesson = lessonService.get(id);
        if(Objects.nonNull(newLesson.getOrderNumber())){
            lesson.setOrderNumber(newLesson.getOrderNumber());
        }
        if(Objects.nonNull(newLesson.getCourse())){
            lesson.setCourse(newLesson.getCourse());
        }
        return new ResponseEntity<>(lessonService.convertEntityToDto(lesson), HttpStatus.OK);
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> deleteLesson(@PathVariable("id") UUID id){
        Lesson lesson = lessonService.get(id);
        lessonService.delete(lesson);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
