package com.example.smartclassroomvisionsystem.model;

import java.util.List;

public class ClassDetails {
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StudentAttendanceEngagement> getAttendedStudents() {
        return attendedStudents;
    }

    public void setAttendedStudents(List<StudentAttendanceEngagement> attendedStudents) {
        this.attendedStudents = attendedStudents;
    }

    public double getAvgAttendance() {
        return avgAttendance;
    }

    public void setAvgAttendance(double avgAttendance) {
        this.avgAttendance = avgAttendance;
    }

    public double getAvgEngagement() {
        return avgEngagement;
    }

    public void setAvgEngagement(double avgEngagement) {
        this.avgEngagement = avgEngagement;
    }

    private String classId;


    private String grade;
    private String subject;
    private String datetime;
    private String teacherId;
    private String status;
    private List<StudentAttendanceEngagement> attendedStudents;
    private double avgAttendance;
    private double avgEngagement;

    @Override
    public String toString() {
        return "ClassDetails{" +
                "classId='" + classId + '\'' +
                ", subject='" + subject + '\'' +
                ", attendedStudents=" + attendedStudents +
                '}';
    }

}
