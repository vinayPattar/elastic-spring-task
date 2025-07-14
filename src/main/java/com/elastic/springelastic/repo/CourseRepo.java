package com.elastic.springelastic.repo;

import com.elastic.springelastic.model.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;

public interface CourseRepo extends ElasticsearchRepository<CourseDocument, Integer> {

}
