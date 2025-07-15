package com.elastic.springelastic.service;

import com.elastic.springelastic.model.CourseDocument;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public Page<CourseDocument> searchCourses(
            String q,
            Integer minAge,
            Integer maxAge,
            String category,
            String type,
            Double minPrice,
            Double maxPrice,
            String startDate,
            String sort,
            int page,
            int size
    ) {

        Criteria criteria = new Criteria();
        criteria = criteria.and(new Criteria("minAge").greaterThanEqual(minAge)); // minAge is null here
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // ✅ Fuzzy Search
        if (q != null && !q.isBlank()) {
            boolQuery.must(QueryBuilders.multiMatchQuery(q, "title", "description")
                    .fuzziness(Fuzziness.AUTO));
        }

        if (minAge != null) {
            criteria = criteria.and(new Criteria("minAge").greaterThanEqual(minAge));
        }

        if (maxAge != null) {
            criteria = criteria.and(new Criteria("maxAge").lessThanEqual(maxAge));
        }

        if (minPrice != null) {
            criteria = criteria.and(new Criteria("price").greaterThanEqual(minPrice));
        }

        if (maxPrice != null) {
            criteria = criteria.and(new Criteria("price").lessThanEqual(maxPrice));
        }

        if (category != null && !category.isBlank()) {
            criteria = criteria.and(new Criteria("category").is(category));
        }

        if (type != null && !type.isBlank()) {
            criteria = criteria.and(new Criteria("type").is(type));
        }

        if (startDate != null && !startDate.isBlank()) {
            criteria = criteria.and(new Criteria("nextSessionDate").greaterThanEqual(Instant.parse(startDate)));
        }


        Sort sortObj = Sort.by("nextSessionDate").ascending();
        if ("priceAsc".equals(sort)) {
            sortObj = Sort.by("price").ascending();
        } else if ("priceDesc".equals(sort)) {
            sortObj = Sort.by("price").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortObj);
        CriteriaQuery query = new CriteriaQuery(criteria, pageable);
        SearchHits<CourseDocument> searchHits = elasticsearchOperations.search(query, CourseDocument.class);

        List<CourseDocument> results = searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();

        return new PageImpl<>(results, pageable, searchHits.getTotalHits());

    }
}
