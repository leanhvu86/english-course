package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Sentence;
import com.selflearning.englishcourses.repository.elasticsearch.SentenceElasticsearchRepository;
import com.selflearning.englishcourses.repository.jpa.SentenceJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentenceServiceTest {

    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private SentenceJpaRepository sentenceJpaRepository;

    @Autowired
    private SentenceElasticsearchRepository sentenceElasticsearchRepository;

    @Test
    public void testSynchronizeElasticsearch(){
        List<Sentence> sentences = sentenceJpaRepository.findAll();
        sentenceElasticsearchRepository.saveAll(sentences);
    }

}
