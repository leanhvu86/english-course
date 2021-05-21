package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.Phrase;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface PhraseElasticsearchRepository extends ElasticsearchRepository<Phrase, UUID> {
}
