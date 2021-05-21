package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private UserService userService;

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private PhraseService phraseService;

    @Override
    public long getTotalSentences() {
        return sentenceService.count();
    }

    @Override
    public long getTotalPhrases() {
        return phraseService.count();
    }

    @Override
    public long getTotalVocabularies() {
        return vocabularyService.count();
    }

    @Override
    public long getTotalUsers() {
        return userService.count();
    }
}
