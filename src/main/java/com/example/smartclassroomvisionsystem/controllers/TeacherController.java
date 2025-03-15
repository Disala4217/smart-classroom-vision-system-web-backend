package com.example.smartclassroomvisionsystem.controllers;

import com.example.smartclassroomvisionsystem.services.TeacherService;  // Update import
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService ts;
    public TeacherController(TeacherService ts) {
        this.ts = ts;
    }

    @PostMapping("/teachers")
    public ResponseEntity<String> addTeacher(@RequestBody Map<String, String> data) {
        String id = data.get("id");
        String name = data.get("name");
        String password = data.get("password");
        String role = data.get("role");
        String grade = data.get("grade");

        boolean isAdded = ts.addTeacher(id,name, password, role, grade);
        if (isAdded) {
            return ResponseEntity.ok("Teacher added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Teacher not added");
        }
    }
}
