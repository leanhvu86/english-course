package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.Sentence;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface SentenceElasticsearchRepository extends ElasticsearchRepository<Sentence, UUID> {

}
