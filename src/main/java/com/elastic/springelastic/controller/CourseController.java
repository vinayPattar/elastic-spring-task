package com.elastic.springelastic.controller;

import com.elastic.springelastic.model.CourseDocument;
import com.elastic.springelastic.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
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
}
