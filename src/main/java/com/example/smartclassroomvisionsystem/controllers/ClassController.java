package com.example.smartclassroomvisionsystem.controllers;

import com.example.smartclassroomvisionsystem.model.ClassDetails;
import com.example.smartclassroomvisionsystem.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/class/lastUnfinished")
    public ResponseEntity<Map<String, Object>> getLastUnfinishedClass(@RequestBody Map<String, String> request) {
        String teacherId = request.get("teacherId");
        System.out.println("Teacher ID: " + teacherId);

        if (teacherId != null && !teacherId.isEmpty()) {
            ClassDetails classDetails = classService.getLastUnfinishedClass(teacherId);
            if (classDetails != null) {
                System.out.println(classDetails);
                return ResponseEntity.ok(Map.of("classDetails", classDetails));
            } else {
                return ResponseEntity.ok(Map.of("message", "No unfinished class found"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Teacher ID is missing"));
        }
    }
}
