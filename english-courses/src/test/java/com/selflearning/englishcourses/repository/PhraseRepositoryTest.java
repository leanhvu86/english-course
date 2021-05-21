package com.selflearning.englishcourses.repository;

import com.selflearning.englishcourses.repository.elasticsearch.PhraseElasticsearchRepository;
import com.selflearning.englishcourses.repository.jpa.PhraseJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhraseRepositoryTest {

    @Autowired
    private PhraseJpaRepository phraseJpaRepository;

    @Autowired
    private PhraseElasticsearchRepository phraseElasticsearchRepository;

    @Test
    public void testSynchronize(){
        phraseElasticsearchRepository.saveAll(phraseJpaRepository.findAll());
    }

}
