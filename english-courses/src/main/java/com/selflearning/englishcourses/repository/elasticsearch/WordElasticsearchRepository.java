package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.Word;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface WordElasticsearchRepository extends ElasticsearchRepository<Word, UUID> {
}
