package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Phrase;
import com.selflearning.englishcourses.service.PhraseService;
import com.selflearning.englishcourses.service.dto.PhraseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PhraseRestController {

    private PhraseService phraseService;

    @Autowired
    public void setVerbPhraseService(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/phrases")
    public ResponseEntity<Page<PhraseDto>> getVerbPhrases(Pageable pageable){
        return new ResponseEntity<>(phraseService.convertEntityPageToDtoPage(phraseService.findAll(pageable)), HttpStatus.OK);
    }

    @PostMapping("/phrases/save-all")
    public ResponseEntity<List<PhraseDto>> createVerbPhrases(@RequestBody List<PhraseDto> phraseDtos) {
        List<Phrase> phrases = phraseService.convertDtoToEntity(phraseDtos);
        phraseService.saveAll(phrases);
        return new ResponseEntity<>(phraseService.convertEntityToDto(phrases), HttpStatus.CREATED);
    }

    @GetMapping(value = "/phrases", params = "search")
    public ResponseEntity<Page<PhraseDto>> search(@RequestParam("search") String value, Pageable pageable){
        return new ResponseEntity<>(phraseService.convertEntityPageToDtoPage(phraseService.search(value, pageable)), HttpStatus.OK);
    }

    @GetMapping("/phrases/count")
    public ResponseEntity<Long> getTotalPhrases(){
        return new ResponseEntity<>(phraseService.count(), HttpStatus.OK);
    }



}
