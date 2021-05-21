package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface UserElasticsearchRepository extends ElasticsearchRepository<User, UUID> {
}
