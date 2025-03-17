package com.example.smartclassroomvisionsystem.controllers;

import com.example.smartclassroomvisionsystem.services.TeacherService;  // Update import
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/teacher/profile/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable String id, @RequestBody Map<String, String> data) {
        String name = data.get("name");
        String password = data.get("password");
        String grade = data.get("grade");

        // Print values to the console
        System.out.println("Teacher ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println("Grade: " + grade);

        boolean isUpdated = ts.updateTeacher(id, name, password, grade);

        if (isUpdated) {
            System.out.println("Teacher updated successfully");
            return ResponseEntity.ok("Teacher profile updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update teacher profile");
        }
    }
    @GetMapping("/teacher/profile/{id}")
    public ResponseEntity<Map<String, String>> getProfile(@PathVariable String id) {
        // Fetch the teacher's profile based on the ID
        Map<String, String> teacherProfile = ts.getTeacherProfile(id);

        if (teacherProfile != null) {

            return ResponseEntity.ok(teacherProfile); // Return the profile data as a response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if teacher not found
        }
    }


}
