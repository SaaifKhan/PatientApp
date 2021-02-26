package com.example.patientsystem;

import android.content.Intent;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class PatientModel implements Serializable {
    String name;
    String fathername;
    String gender;
    String age;

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    String Consultancytype;
    String patientstatus;
    String id;

    public PatientModel(String name, String fathername, String gender, String age, String consultancytype, String patientstatus, String id) {
        this.name = name;
        this.fathername = fathername;
        this.gender = gender;
        this.age = age;
        Consultancytype = consultancytype;
        this.patientstatus = patientstatus;
        this.id=id;
    }

    public PatientModel() {
    }

    public String getPatientstatus() {
        return patientstatus;
    }

    public void setPatientstatus(String patientstatus) {
        this.patientstatus = patientstatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConsultancytype() {
        return Consultancytype;
    }

    public void setConsultancytype(String consultancytype) {
        Consultancytype = consultancytype;
    }

}
