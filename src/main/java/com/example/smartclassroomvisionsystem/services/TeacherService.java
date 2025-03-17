package com.example.smartclassroomvisionsystem.services;

import com.example.smartclassroomvisionsystem.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    public boolean addTeacher(String ID, String name, String password, String role, String grade) {
        String query = "INSERT INTO teacher (id,name, password, role, grade) VALUES (?,?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ID);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.setString(5, grade);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeacher(String ID, String name, String password, String grade) {
        String query = "UPDATE teacher SET name = ?, password = ?, grade = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, grade);
            stmt.setString(4, ID);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Map<String, String> getTeacherProfile(String id) {
        String query = "SELECT * FROM teacher WHERE id = ?";
        Map<String, String> teacherProfile = new HashMap<>();

        try (Connection connection = DataBaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                // Populate the map with the teacher's profile data
                teacherProfile.put("name", resultSet.getString("name"));
                teacherProfile.put("role", resultSet.getString("role"));
                teacherProfile.put("grade", resultSet.getString("grade"));
                return teacherProfile;
            } else {
                return null; // Teacher not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
