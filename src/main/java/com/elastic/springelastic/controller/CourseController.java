package com.elastic.springelastic.controller;

import com.elastic.springelastic.DTO.SearchResponse;
import com.elastic.springelastic.model.CourseDocument;
import com.elastic.springelastic.service.CourseSearchService;
import com.elastic.springelastic.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private final CourseService service;
    private final CourseSearchService searchService;

    public CourseController(CourseService service, CourseSearchService searchService) {
        this.service = service;
        this.searchService = searchService;
    }

    @PostMapping("course/add-course")
    public ResponseEntity<CourseDocument> addCourse(@RequestBody CourseDocument course){
        try {
            return new ResponseEntity<>(service.createCourse(course), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("course/update-course/{id}")
    public ResponseEntity<CourseDocument> updateCourse(@PathVariable Integer id,
                                                       @RequestBody CourseDocument course) {
        try {
            return new ResponseEntity<>(service.updateCourse(id, course), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("course/delete-course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(service.deleteCourse(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("course")
    public ResponseEntity<Iterable<CourseDocument>> getCourses() {
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

//    @GetMapping("course/search")
//    public Page<CourseDocument> searchCourses(
//            @RequestParam String q,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size) {
//        return service.searchCourses(q, page, size);
//    }

    @GetMapping("/course/search")
    public SearchResponse<CourseDocument> searchCourses(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CourseDocument> resultPage = searchService.searchCourses(
                q, minAge, maxAge, category, type, minPrice, maxPrice, startDate, sort, page, size
        );

        return new SearchResponse<>(resultPage.getTotalElements(), resultPage.getContent());
    }



}
