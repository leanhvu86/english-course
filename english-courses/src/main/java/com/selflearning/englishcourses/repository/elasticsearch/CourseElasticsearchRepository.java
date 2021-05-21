package com.selflearning.englishcourses.repository.elasticsearch;

import com.selflearning.englishcourses.domain.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface CourseElasticsearchRepository extends ElasticsearchRepository<Course, UUID> {

}
