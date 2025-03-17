package com.example.smartclassroomvisionsystem.services;

import com.example.smartclassroomvisionsystem.DataBaseConnection;
import com.example.smartclassroomvisionsystem.model.ClassDetails;
import com.example.smartclassroomvisionsystem.model.StudentAttendanceEngagement;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {

    // Method to get the last unfinished class of a teacher
    public ClassDetails getLastUnfinishedClass(String teacherId) {
        ClassDetails classDetails = null;

        // First, get the last unfinished class for the teacher
        String classQuery = "SELECT c.Class_id, c.grade, c.subject, c.datetime, c.teacher_id, c.Status " +
                "FROM class c WHERE c.teacher_id = ? AND c.Status != 'finished' ORDER BY c.datetime DESC LIMIT 1";

        try (Connection connection = DataBaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(classQuery)) {
            stmt.setString(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String classId = rs.getString("Class_id");
                String grade = rs.getString("grade");
                String subject = rs.getString("subject");
                String datetime = rs.getString("datetime");
                String status = rs.getString("Status");

                // Fetch the attended students and their engagement and attendance levels
                List<StudentAttendanceEngagement> attendedStudents = getAttendedStudents(classId);

                // Calculate the average attendance and engagement for the grade
                double[] avgValues = getAvgAttendanceAndEngagement(grade);

                double avgAttendance = avgValues[0];
                double avgEngagement = avgValues[1];

                // Create and set class details
                classDetails = new ClassDetails();
                classDetails.setClassId(classId);
                classDetails.setGrade(grade);
                classDetails.setSubject(subject);
                classDetails.setDatetime(datetime);
                classDetails.setTeacherId(teacherId);
                classDetails.setStatus(status);
                classDetails.setAttendedStudents(attendedStudents);
                classDetails.setAvgAttendance(avgAttendance);
                classDetails.setAvgEngagement(avgEngagement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classDetails;
    }

    // Helper method to get the list of attended students for a given class
    private List<StudentAttendanceEngagement> getAttendedStudents(String classId) {
        List<StudentAttendanceEngagement> students = new ArrayList<>();

        String query = "SELECT s.id AS student_id, s.name AS student_name, a.attendance, a.engagement_level " +
                "FROM attendance a " +
                "JOIN student s ON a.student_id = s.id " +
                "WHERE a.class_id = ?";

        try (Connection connection = DataBaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, classId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                double attendance = rs.getDouble("attendance");
                double engagementLevel = rs.getDouble("engagement_level");

                students.add(new StudentAttendanceEngagement(studentId, studentName, attendance, engagementLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Helper method to get the average attendance and engagement for all students in the grade
    private double[] getAvgAttendanceAndEngagement(String grade) {
        double avgAttendance = 0;
        double avgEngagement = 0;

        String query = "SELECT AVG(a.attendance) AS avg_attendance, AVG(a.engagement_level) AS avg_engagement " +
                "FROM attendance a " +
                "JOIN class c ON a.class_id = c.Class_id " +
                "WHERE c.grade = ?";

        try (Connection connection = DataBaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, grade);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                avgAttendance = rs.getDouble("avg_attendance");
                avgEngagement = rs.getDouble("avg_engagement");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new double[]{avgAttendance, avgEngagement};
    }
}
