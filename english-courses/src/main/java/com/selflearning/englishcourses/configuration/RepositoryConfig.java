package com.selflearning.englishcourses.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.selflearning.englishcourses.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.selflearning.englishcourses.repository.elasticsearch")
public class RepositoryConfig {

}
