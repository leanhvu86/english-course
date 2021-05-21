package com.selflearning.englishcourses.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceTest {

    private WordService wordService;

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

}
