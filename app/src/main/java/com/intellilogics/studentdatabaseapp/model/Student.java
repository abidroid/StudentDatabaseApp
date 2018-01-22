package com.intellilogics.studentdatabaseapp.model;

import java.io.Serializable;

/**
 * Created by abidroid on 12/27/2017.
 */

// POJO

public class Student implements Serializable{

    int id;
    String name;
    String course;
    String contact;
    int totalFee;
    int feePaid;

    public Student(String name, String course, String contact, int totalFee, int feePaid) {
        this.name = name;
        this.course = course;
        this.contact = contact;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
    }

    public Student(int id, String name, String course, String contact, int totalFee, int feePaid) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.contact = contact;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(int feePaid) {
        this.feePaid = feePaid;
    }

    @Override
    public String toString() {
        return name;
    }
}
