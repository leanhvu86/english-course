package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Sentence;
import com.selflearning.englishcourses.service.SentenceService;
import com.selflearning.englishcourses.service.dto.SentenceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

/**
 * Sentence Rest API
 * @author manhnd
 */
@RestController
@RequestMapping("/api/v1")
public class SentenceRestController {

    private SentenceService sentenceService;

    @Autowired
    public void setSentenceService(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @Value("${base-path}")
    private String path;

    /**
     * Create sentence
     * @param sentenceDto
     * @return SentenceDto
     */
    @PostMapping("/sentences")
    public ResponseEntity<SentenceDto> createSentence(@Valid @RequestBody SentenceDto sentenceDto) {
        Sentence sentence = sentenceService.convertDtoToEntity(sentenceDto);
        sentenceService.save(sentence);
        return new ResponseEntity<>(sentenceService.convertEntityToDto(sentence), HttpStatus.CREATED);
    }

    /**
     * Create many sentences
     * @param sentenceDtos List of SentenceDto
     * @return List SentenceDto
     */
    @PostMapping("/sentences/save-all")
    public ResponseEntity<List<SentenceDto>> createSentences(@RequestBody List<SentenceDto> sentenceDtos) {
        List<Sentence> sentences = sentenceService.convertDtoToEntity(sentenceDtos);
        sentenceService.saveAll(sentences);
        return new ResponseEntity<>(sentenceService.convertEntityToDto(sentences), HttpStatus.CREATED);
    }

    /**
     * Update sentence
     * @param id
     * @param sentenceDto
     * @return
     */
    @PutMapping("/sentences/{id}")
    public ResponseEntity<SentenceDto> updateSentence(@PathVariable("id") UUID id, @RequestBody SentenceDto sentenceDto){
        Sentence sentence = sentenceService.convertDtoToEntity(sentenceDto);
        sentenceService.save(sentence);
        return new ResponseEntity<>(sentenceService.convertEntityToDto(sentence), HttpStatus.OK);
    }

    @GetMapping("/sentences")
    public ResponseEntity<Page<SentenceDto>> getSentences(Pageable pageable) {
        return new ResponseEntity<>(sentenceService.convertEntityPageToDtoPage(sentenceService.findAll(pageable)), HttpStatus.OK);
    }

    @GetMapping("/sentences/{id}")
    public ResponseEntity<SentenceDto> getSentence(@PathVariable UUID id) {
        Sentence sentence = sentenceService.get(id);
        return new ResponseEntity<>(sentenceService.convertEntityToDto(sentence), HttpStatus.OK);
    }

    @GetMapping(value = "/sentences", params = "search")
    public ResponseEntity<Page<SentenceDto>> searchSentence(@RequestParam("search") String value, Pageable pageable) {
        return new ResponseEntity<>(sentenceService.convertEntityPageToDtoPage(sentenceService.search(value, pageable)),
                HttpStatus.OK);
    }

    @GetMapping("/sentences/{id}/audio")
    public ResponseEntity<byte[]> getAudio(@PathVariable UUID id) throws IOException {
        Sentence sentence = sentenceService.get(id);
        String audioPath = sentence.getAudioPath();
        File file = new File(this.path + audioPath);
        Resource resource = new FileSystemResource(file);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(bytes);
    }

    @GetMapping("/sentences/count")
    public ResponseEntity<Long> getTotalSentences(){
        return new ResponseEntity<>(sentenceService.count(), HttpStatus.OK);
    }

}
