package com.elastic.springelastic.util;

import com.elastic.springelastic.model.CourseDocument;
import com.elastic.springelastic.repo.CourseRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CourseRepo courseRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/sample-courses.json");
        Iterable<CourseDocument> courses = Arrays.asList(objectMapper.readValue(inputStream, CourseDocument[].class));
        courseRepository.saveAll(courses);
        System.out.println("Sample courses indexed successfully.");
    }
}

