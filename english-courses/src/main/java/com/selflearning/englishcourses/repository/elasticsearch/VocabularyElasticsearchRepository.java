package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.Vocabulary;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface VocabularyElasticsearchRepository extends ElasticsearchRepository<Vocabulary, UUID> {
}
