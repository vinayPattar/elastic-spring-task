package com.elastic.springelastic.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "course")
@Builder
public class CourseDocument {

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)  // Category is usually exact match
    private String category;

    @Field(type = FieldType.Keyword)  // Type: ONE_TIME, COURSE, CLUB
    private String type;

    @Field(type = FieldType.Keyword)
    private String gradeRange;

    @Field(type = FieldType.Integer)
    private Integer minAge;

    @Field(type = FieldType.Integer)
    private Integer maxAge;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date)
    private Instant nextSessionDate;

}
