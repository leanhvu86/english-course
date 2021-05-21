package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.Role;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface RoleElasticsearchRepository extends ElasticsearchRepository<Role, UUID> {
}
