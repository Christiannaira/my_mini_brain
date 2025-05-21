package com.example.myminibrain;

public class Item {

    String courseTitle;
    String courseDes;

    public Item(String courseTitle, String courseDes) {
        this.courseTitle = courseTitle;
        this.courseDes = courseDes;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }
}
