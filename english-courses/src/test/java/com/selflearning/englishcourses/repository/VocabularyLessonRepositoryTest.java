package com.selflearning.englishcourses.repository;

import com.selflearning.englishcourses.domain.*;
import com.selflearning.englishcourses.repository.jpa.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VocabularyLessonRepositoryTest {


    @Autowired
    private LessonModuleJpaRepository lessonModuleJpaRepository;

    @Autowired
    private LessonJpaRepository lessonJpaRepository;

    @Autowired
    private ModuleJpaRepository moduleJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private SentenceJpaRepository sentenceJpaRepository;

    @Autowired
    private VocabularyJpaRepository vocabularyJpaRepository;

    @Autowired
    private VocabularyLessonJpaRepository vocabularyLessonJpaRepository;

    @Test
    public void getVocabularyLesson(){
        Optional<Lesson> lesson = lessonJpaRepository.findByCourseAndOrderNumber(courseJpaRepository.findByName("Tiếng Anh giao tiếp 360").get(), 1);
        Optional<Module> module = moduleJpaRepository.findByName("Luyện từ vựng");
        LessonModule lessonModule = lessonModuleJpaRepository.findByLessonAndModule(lesson.get(), module.get()).get();
        VocabularyLesson vocabularyLesson = lessonModule.getVocabularyLesson();
        List<VocabularyLessonDetail> vocabularyLessonDetails = vocabularyLesson.getVocabularyLessonDetails();
        Assert.notNull(vocabularyLessonDetails);
    }

    @Test
    public void testCreateVocabularyLesson(){
        VocabularyLesson vocabularyLesson = new VocabularyLesson();
        Optional<Lesson> lesson = lessonJpaRepository.findByCourseAndOrderNumber(courseJpaRepository.findByName("Tiếng Anh giao tiếp 360").get(), 5);
        Optional<Module> module = moduleJpaRepository.findByName("Luyện từ vựng");
        LessonModule lessonModule = lessonModuleJpaRepository.findByLessonAndModule(lesson.get(), module.get()).get();
        lessonModule.setVocabularyLesson(vocabularyLesson);
        vocabularyLesson.setLessonModule(lessonModule);
        List<VocabularyLessonDetail> vocabularyLessonDetails = createVocabularyLessonDetails5(vocabularyLesson);
        vocabularyLesson.setVocabularyLessonDetails(vocabularyLessonDetails);
        lessonModuleJpaRepository.save(lessonModule);
    }

    private List<VocabularyLessonDetail> createVocabularyLessonDetails(VocabularyLesson vocabularyLesson){
        List<VocabularyLessonDetail> vocabularyLessonDetails = new ArrayList<>();
        VocabularyLessonDetail vocabularyLessonDetail1 = new VocabularyLessonDetail();

        vocabularyLessonDetail1.setSentence(sentenceJpaRepository.findByText("Call me when you've arrived there."));
        vocabularyLessonDetail1.setVocabulary(vocabularyJpaRepository.findByWordText("call"));
        vocabularyLessonDetail1.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail1);

        VocabularyLessonDetail vocabularyLessonDetail2 = new VocabularyLessonDetail();
        vocabularyLessonDetail2.setSentence(sentenceJpaRepository.findByText("He didn't call or text me all day."));
        vocabularyLessonDetail2.setVocabulary(vocabularyJpaRepository.findByWordText("text"));
        vocabularyLessonDetail2.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail2);

        VocabularyLessonDetail vocabularyLessonDetail3 = new VocabularyLessonDetail();
        vocabularyLessonDetail3.setSentence(sentenceJpaRepository.findByText("Sorry, I forgot his name."));
        vocabularyLessonDetail3.setVocabulary(vocabularyJpaRepository.findByWordText("forget"));
        vocabularyLessonDetail3.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail3);

        VocabularyLessonDetail vocabularyLessonDetail4 = new VocabularyLessonDetail();
        vocabularyLessonDetail4.setSentence(sentenceJpaRepository.findByText("There's a message for you here from Peter."));
        vocabularyLessonDetail4.setVocabulary(vocabularyJpaRepository.findByWordText("message"));
        vocabularyLessonDetail4.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail4);

        VocabularyLessonDetail vocabularyLessonDetail5 = new VocabularyLessonDetail();
        vocabularyLessonDetail5.setSentence(sentenceJpaRepository.findByText("She spent hours on the phone chatting to her friends."));
        vocabularyLessonDetail5.setVocabulary(vocabularyJpaRepository.findByWordText("chat"));
        vocabularyLessonDetail5.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail5);
        return vocabularyLessonDetails;
    }

    private List<VocabularyLessonDetail> createVocabularyLessonDetails2(VocabularyLesson vocabularyLesson){
        List<VocabularyLessonDetail> vocabularyLessonDetails = new ArrayList<>();
        VocabularyLessonDetail vocabularyLessonDetail1 = new VocabularyLessonDetail();

        vocabularyLessonDetail1.setSentence(sentenceJpaRepository.findByText("I can't get to sleep if there's any noise."));
        vocabularyLessonDetail1.setVocabulary(vocabularyJpaRepository.findByWordText("sleep"));
        vocabularyLessonDetail1.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail1);

        VocabularyLessonDetail vocabularyLessonDetail2 = new VocabularyLessonDetail();
        vocabularyLessonDetail2.setSentence(sentenceJpaRepository.findByText("I had a strange dream last night."));
        vocabularyLessonDetail2.setVocabulary(vocabularyJpaRepository.findByWordText("dream"));
        vocabularyLessonDetail2.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail2);

        VocabularyLessonDetail vocabularyLessonDetail3 = new VocabularyLessonDetail();
        vocabularyLessonDetail3.setSentence(sentenceJpaRepository.findByText("Is sleepwalking dangerous?"));
        vocabularyLessonDetail3.setVocabulary(vocabularyJpaRepository.findByWordText("sleepwalking"));
        vocabularyLessonDetail3.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail3);

        VocabularyLessonDetail vocabularyLessonDetail4 = new VocabularyLessonDetail();
        vocabularyLessonDetail4.setSentence(sentenceJpaRepository.findByText("Did you have a nightmare last night?"));
        vocabularyLessonDetail4.setVocabulary(vocabularyJpaRepository.findByWordText("nightmare"));
        vocabularyLessonDetail4.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail4);

        VocabularyLessonDetail vocabularyLessonDetail5 = new VocabularyLessonDetail();
        vocabularyLessonDetail5.setSentence(sentenceJpaRepository.findByText("The whole family sleeps in on Sundays."));
        vocabularyLessonDetail5.setVocabulary(vocabularyJpaRepository.findByWordText("sleep in"));
        vocabularyLessonDetail5.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail5);
        return vocabularyLessonDetails;
    }

    private List<VocabularyLessonDetail> createVocabularyLessonDetails3(VocabularyLesson vocabularyLesson){
        List<VocabularyLessonDetail> vocabularyLessonDetails = new ArrayList<>();
        VocabularyLessonDetail vocabularyLessonDetail1 = new VocabularyLessonDetail();

        vocabularyLessonDetail1.setSentence(sentenceJpaRepository.findByText("We had a fun day at the park."));
        vocabularyLessonDetail1.setVocabulary(vocabularyJpaRepository.findByWordText("fun"));
        vocabularyLessonDetail1.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail1);

        VocabularyLessonDetail vocabularyLessonDetail2 = new VocabularyLessonDetail();
        vocabularyLessonDetail2.setSentence(sentenceJpaRepository.findByText("Oh, I'm so bored!"));
        vocabularyLessonDetail2.setVocabulary(vocabularyJpaRepository.findByWordText("bored"));
        vocabularyLessonDetail2.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail2);

        VocabularyLessonDetail vocabularyLessonDetail3 = new VocabularyLessonDetail();
        vocabularyLessonDetail3.setSentence(sentenceJpaRepository.findByText("I find swimming very relaxing."));
        vocabularyLessonDetail3.setVocabulary(vocabularyJpaRepository.findByWordText("relaxing"));
        vocabularyLessonDetail3.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail3);

        VocabularyLessonDetail vocabularyLessonDetail4 = new VocabularyLessonDetail();
        vocabularyLessonDetail4.setSentence(sentenceJpaRepository.findByText("I'm so tired."));
        vocabularyLessonDetail4.setVocabulary(vocabularyJpaRepository.findByWordText("tired"));
        vocabularyLessonDetail4.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail4);

        VocabularyLessonDetail vocabularyLessonDetail5 = new VocabularyLessonDetail();
        vocabularyLessonDetail5.setSentence(sentenceJpaRepository.findByText("I hate this feeling!"));
        vocabularyLessonDetail5.setVocabulary(vocabularyJpaRepository.findByWordText("feeling"));
        vocabularyLessonDetail5.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail5);
        return vocabularyLessonDetails;
    }

    private List<VocabularyLessonDetail> createVocabularyLessonDetails4(VocabularyLesson vocabularyLesson){
        List<VocabularyLessonDetail> vocabularyLessonDetails = new ArrayList<>();
        VocabularyLessonDetail vocabularyLessonDetail1 = new VocabularyLessonDetail();

        vocabularyLessonDetail1.setSentence(sentenceJpaRepository.findByText("He works for a large oil company."));
        vocabularyLessonDetail1.setVocabulary(vocabularyJpaRepository.findByWordText("company"));
        vocabularyLessonDetail1.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail1);

        VocabularyLessonDetail vocabularyLessonDetail2 = new VocabularyLessonDetail();
        vocabularyLessonDetail2.setSentence(sentenceJpaRepository.findByText("I don't wanna work in a building downtown."));
        vocabularyLessonDetail2.setVocabulary(vocabularyJpaRepository.findByWordText("building"));
        vocabularyLessonDetail2.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail2);

        VocabularyLessonDetail vocabularyLessonDetail3 = new VocabularyLessonDetail();
        vocabularyLessonDetail3.setSentence(sentenceJpaRepository.findByText("I'm my own boss."));
        vocabularyLessonDetail3.setVocabulary(vocabularyJpaRepository.findByWordText("boss"));
        vocabularyLessonDetail3.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail3);

        VocabularyLessonDetail vocabularyLessonDetail4 = new VocabularyLessonDetail();
        vocabularyLessonDetail4.setSentence(sentenceJpaRepository.findByText("Their employees worked a ten-hour day."));
        vocabularyLessonDetail4.setVocabulary(vocabularyJpaRepository.findByWordText("employee"));
        vocabularyLessonDetail4.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail4);

        VocabularyLessonDetail vocabularyLessonDetail5 = new VocabularyLessonDetail();
        vocabularyLessonDetail5.setSentence(sentenceJpaRepository.findByText("The goods have been sitting in a warehouse for months."));
        vocabularyLessonDetail5.setVocabulary(vocabularyJpaRepository.findByWordText("warehouse"));
        vocabularyLessonDetail5.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail5);
        return vocabularyLessonDetails;
    }

    private List<VocabularyLessonDetail> createVocabularyLessonDetails5(VocabularyLesson vocabularyLesson){
        List<VocabularyLessonDetail> vocabularyLessonDetails = new ArrayList<>();
        VocabularyLessonDetail vocabularyLessonDetail1 = new VocabularyLessonDetail();

        vocabularyLessonDetail1.setSentence(sentenceJpaRepository.findByText("We went walking through the woods."));
        vocabularyLessonDetail1.setVocabulary(vocabularyJpaRepository.findByWordText("walking"));
        vocabularyLessonDetail1.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail1);

        VocabularyLessonDetail vocabularyLessonDetail2 = new VocabularyLessonDetail();
        vocabularyLessonDetail2.setSentence(sentenceJpaRepository.findByText("Writing is tough."));
        vocabularyLessonDetail2.setVocabulary(vocabularyJpaRepository.findByWordText("writing"));
        vocabularyLessonDetail2.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail2);

        VocabularyLessonDetail vocabularyLessonDetail3 = new VocabularyLessonDetail();
        vocabularyLessonDetail3.setSentence(sentenceJpaRepository.findByText("I don't like shopping very much."));
        vocabularyLessonDetail3.setVocabulary(vocabularyJpaRepository.findByWordText("shopping"));
        vocabularyLessonDetail3.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail3);

        VocabularyLessonDetail vocabularyLessonDetail4 = new VocabularyLessonDetail();
        vocabularyLessonDetail4.setSentence(sentenceJpaRepository.findByText("I enjoy winter sports."));
        vocabularyLessonDetail4.setVocabulary(vocabularyJpaRepository.findByWordText("sport"));
        vocabularyLessonDetail4.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail4);

        VocabularyLessonDetail vocabularyLessonDetail5 = new VocabularyLessonDetail();
        vocabularyLessonDetail5.setSentence(sentenceJpaRepository.findByText("Let's go swimming!"));
        vocabularyLessonDetail5.setVocabulary(vocabularyJpaRepository.findByWordText("swimming"));
        vocabularyLessonDetail5.setVocabularyLesson(vocabularyLesson);
        vocabularyLessonDetails.add(vocabularyLessonDetail5);
        return vocabularyLessonDetails;
    }

}
