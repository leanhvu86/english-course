package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Vocabulary;
import com.selflearning.englishcourses.repository.elasticsearch.VocabularyElasticsearchRepository;
import com.selflearning.englishcourses.repository.jpa.VocabularyJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VocabularyServiceTest {

    private VocabularyService vocabularyService;


    private VocabularyJpaRepository vocabularyJpaRepository;

    private VocabularyElasticsearchRepository vocabularyElasticsearchRepository;

    @Autowired
    public void setVocabularyService(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @Autowired
    public void setVocabularyJpaRepository(VocabularyJpaRepository vocabularyJpaRepository) {
        this.vocabularyJpaRepository = vocabularyJpaRepository;
    }

    @Autowired
    public void setVocabularyElasticsearchRepository(VocabularyElasticsearchRepository vocabularyElasticsearchRepository) {
        this.vocabularyElasticsearchRepository = vocabularyElasticsearchRepository;
    }

    @Test
    public void synchronizeMysqlToElasticsearch(){
        List<Vocabulary> all = vocabularyJpaRepository.findAll();
        vocabularyElasticsearchRepository.saveAll(all);
    }

    @Test
    public void testRenameImagePath(){
//        List<Vocabulary> vocabularies = vocabularyJpaRepository.findAll();
//        vocabularies.forEach(vocabulary -> {
//            String imagePath = vocabulary.getImagePath();
//            imagePath = imagePath.replaceAll("\\\\", "/").replaceAll("E:/Documents/EngProjectData/i", "/I");
//            vocabularyJpaRepository.updateVocabularyImagePath(imagePath, vocabulary.getId());
//        });
    }

}
