package com.example.smartclassroomvisionsystem.model;

public class StudentAttendanceEngagement {


    private String studentId;
    private String studentName;

    public StudentAttendanceEngagement(String studentId, String studentName, double attendance, double engagementLevel) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendance = attendance;
        this.engagementLevel = engagementLevel;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getEngagementLevel() {
        return engagementLevel;
    }

    public void setEngagementLevel(double engagementLevel) {
        this.engagementLevel = engagementLevel;
    }

    private double attendance;
    private double engagementLevel;

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", attendance=" + attendance +
                ", engagementLevel=" + engagementLevel +
                '}';
    }

}
