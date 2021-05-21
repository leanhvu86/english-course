package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Word;
import com.selflearning.englishcourses.service.WordService;
import com.selflearning.englishcourses.service.dto.WordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WordRestController {

    private WordService wordService;

    @GetMapping("/words")
    public ResponseEntity<Page<WordDto>> findAll(Pageable pageable){
        Page<Word> wordPage = wordService.findAll(pageable);
        List<WordDto> wordDtos = wordService.convertEntityToDto(wordPage.getContent());
        long total = wordPage.getTotalElements();
        Page<WordDto> wordDtoPage = new PageImpl<>(wordDtos, pageable, total);
        return new ResponseEntity<>(wordDtoPage, HttpStatus.OK);
    }

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }
}
