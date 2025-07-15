package com.elastic.springelastic.service;

import com.elastic.springelastic.model.CourseDocument;
import com.elastic.springelastic.repo.CourseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepo repo;

    public CourseService(CourseRepo repo) {
        this.repo = repo;
    }

    public CourseDocument createCourse(CourseDocument course) {
        return repo.save(course);
    }

    public CourseDocument updateCourse(Integer id, CourseDocument course) {
        CourseDocument courseDocument = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found") );

        courseDocument.setTitle(course.getTitle());
        courseDocument.setDescription(course.getDescription());
        courseDocument.setCategory(course.getCategory());
        courseDocument.setType(course.getType());
        courseDocument.setPrice(course.getPrice());
        courseDocument.setGradeRange(course.getGradeRange());
        courseDocument.setMaxAge(course.getMaxAge());
        courseDocument.setMinAge(course.getMinAge());
        courseDocument.setNextSessionDate(course.getNextSessionDate());

        return repo.save(courseDocument);

    }

    public String deleteCourse(Integer id) {

         repo.deleteById(id);
         return "Course deleted successfully!";
    }

    public Iterable<CourseDocument> getAllCourses() {
        return repo.findAll();
    }

    public Page<CourseDocument> searchCourses(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findByTitleContainingOrDescriptionContaining(keyword, keyword, keyword,keyword, pageable);
    }

}
