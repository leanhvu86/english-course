package com.selflearning.englishcourses.elasticsearch;

import com.selflearning.englishcourses.domain.Sentence;
import com.selflearning.englishcourses.repository.elasticsearch.SentenceElasticsearchRepository;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import static org.elasticsearch.index.query.QueryBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentenceElasticsearchCustomRepositoryElasticsearchTest {

    @Autowired
    private SentenceElasticsearchRepository sentenceElasticsearchRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testFindByKeyword(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery("Tôi không biết", "text", "meaning"))
                .withPageable(PageRequest.of(0, 10))
                .build();
        Page<Sentence> search = sentenceElasticsearchRepository.search(searchQuery);
        search.forEach(e->{
            System.out.println(e.getText());
        });
        Assert.notNull(search);
    }
}
