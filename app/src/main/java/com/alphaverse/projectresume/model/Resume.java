package com.alphaverse.projectresume.model;


import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alphaverse.projectresume.room.Repository;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;

import java.util.List;

public class Resume  {
private int profileId;
private Context context;
private PersonalDetails resumePersonalDetails;
private List<Qualification> resumeQualification;
private List<Skills> resumeSkills;
private List<Strengths> resumeStrength;
private List<Projects> resumeProjects;
private List<Achievements> resumeAchievements;
private List<IndustrialVisit> resumeIndustrialVisits;



    public Resume(int profileId, Context context) {
        this.profileId = profileId;
        this.context = context;
    }

    public Resume(PersonalDetails resumePersonalDetails, List<Qualification> resumeQualification, List<Skills> resumeSkills, List<Strengths> resumeStrength, List<Projects> resumeProjects, List<Achievements> resumeAchievements, List<IndustrialVisit> resumeIndustrialVisits) {
        this.resumePersonalDetails = resumePersonalDetails;
        this.resumeQualification = resumeQualification;
        this.resumeSkills = resumeSkills;
        this.resumeStrength = resumeStrength;
        this.resumeProjects = resumeProjects;
        this.resumeAchievements = resumeAchievements;
        this.resumeIndustrialVisits = resumeIndustrialVisits;
    }

    public void setResumePersonalDetails(PersonalDetails resumePersonalDetails) {
        this.resumePersonalDetails = resumePersonalDetails;
    }

    public void setResumeQualification(List<Qualification> resumeQualification) {
        this.resumeQualification = resumeQualification;
    }

    public void setResumeSkills(List<Skills> resumeSkills) {
        this.resumeSkills = resumeSkills;
    }

    public void setResumeStrength(List<Strengths> resumeStrength) {
        this.resumeStrength = resumeStrength;
    }

    public void setResumeProjects(List<Projects> resumeProjects) {
        this.resumeProjects = resumeProjects;
    }

    public void setResumeAchievements(List<Achievements> resumeAchievements) {
        this.resumeAchievements = resumeAchievements;
    }

    public void setResumeIndustrialVisits(List<IndustrialVisit> resumeIndustrialVisits) {
        this.resumeIndustrialVisits = resumeIndustrialVisits;
    }

    public PersonalDetails getResumePersonalDetails() {
        return resumePersonalDetails;
    }

    public List<Qualification> getResumeQualification() {
        return resumeQualification;
    }

    public List<Skills> getResumeSkills() {
        return resumeSkills;
    }

    public List<Strengths> getResumeStrength() {
        return resumeStrength;
    }

    public List<Projects> getResumeProjects() {
        return resumeProjects;
    }

    public List<Achievements> getResumeAchievements() {
        return resumeAchievements;
    }

    public List<IndustrialVisit> getResumeIndustrialVisits() {
        return resumeIndustrialVisits;
    }



}
