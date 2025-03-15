package com.example.smartclassroomvisionsystem.services;

import com.example.smartclassroomvisionsystem.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    public boolean addTeacher(String ID,String name, String password, String role, String grade) {
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
}
