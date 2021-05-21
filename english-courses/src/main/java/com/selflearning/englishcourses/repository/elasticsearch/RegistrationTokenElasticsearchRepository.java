package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.RegistrationToken;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface RegistrationTokenElasticsearchRepository extends ElasticsearchRepository<RegistrationToken, UUID> {
}
