package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.WordClass;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface WordClassElasticsearchRepository extends ElasticsearchRepository<WordClass, UUID> {
}
