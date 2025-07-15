package com.elastic.springelastic.repo;

import com.elastic.springelastic.model.CourseDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends ElasticsearchRepository<CourseDocument, Integer> {
    Page<CourseDocument> findByTitleContainingOrDescriptionContaining(
            String titleKeyword,
            String descriptionKeyword,
            String categoryKeyword,
            String typeKeyword,
            Pageable pageable
    );

    List<CourseDocument> findByCategory(String category);

    List<CourseDocument> findByType(String type);
}
