package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Vocabulary;
import com.selflearning.englishcourses.service.VocabularyService;
import com.selflearning.englishcourses.service.dto.VocabularyDto;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class VocabularyRestController {

    private VocabularyService vocabularyService;

    @Value("${base-path}")
    private String path;

    @Autowired
    public void setVocabularyService(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("/vocabularies")
    public ResponseEntity<Page<VocabularyDto>> getVocabularies(Pageable pageable) {
        Page<Vocabulary> vocabularyPage = vocabularyService.findAll(pageable);
        return new ResponseEntity<>(vocabularyService.convertEntityPageToDtoPage(vocabularyPage), HttpStatus.OK);
    }

    @GetMapping(value = "/vocabularies", params = "search")
    public ResponseEntity<Page<VocabularyDto>> searchVocabularies(@RequestParam("search") String value, Pageable pageable){
        Page<Vocabulary> vocabularyPage = vocabularyService.search(value, pageable);
        return new ResponseEntity<>(vocabularyService.convertEntityPageToDtoPage(vocabularyPage), HttpStatus.OK);
    }

    @PostMapping("/vocabularies/save-all")
    public ResponseEntity<List<VocabularyDto>> saveAll(@RequestBody List<VocabularyDto> vocabularyDtos) {
        List<Vocabulary> vocabularies = vocabularyService.convertDtoToEntity(vocabularyDtos);
        vocabularyService.saveAll(vocabularies);
        vocabularyDtos = vocabularyService.convertEntityToDto(vocabularies);
        return new ResponseEntity<>(vocabularyDtos, HttpStatus.CREATED);
    }

    @GetMapping("/vocabularies/{id}/audio")
    public ResponseEntity<byte[]> getVocabularyAudios(@PathVariable("id") UUID id) throws IOException {
        Vocabulary vocabulary = vocabularyService.get(id);
        String audioPath = vocabulary.getWord().getSpecialAudioPath();
        File file = new File(this.path + audioPath);
        Resource resource = new FileSystemResource(file);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(bytes);
    }

    @GetMapping("/vocabularies/{id}/image")
    public ResponseEntity<byte[]> getVocabularyImage(@PathVariable("id") UUID id) throws IOException {
        Vocabulary vocabulary = vocabularyService.get(id);
        String imagePath = vocabulary.getImagePath();
        File file = new File(this.path + imagePath);
        Resource resource = new FileSystemResource(file);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.IMAGE_JPEG))
                .body(bytes);
    }

    @GetMapping("/vocabularies/count")
    public ResponseEntity<Long> getTotalVocabularies(){
        return new ResponseEntity<>(vocabularyService.count(), HttpStatus.OK);
    }

}
