package com.sg.classroster.dto;


//DTO DATA
public class Student {
    private String firstName;
    private String lastName;
    private String studentId;
    //Programming  langugage + cohort month/year
    private String cohort;

    public Student(String studentId){
        this.studentId = studentId;
    }

    public String getStudentId(){
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }
}
