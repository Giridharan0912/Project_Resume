package com.alphaverse.projectresume.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.PersonalDetails;
import com.alphaverse.projectresume.model.Profile;
import com.alphaverse.projectresume.model.Projects;
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;
import com.alphaverse.projectresume.room.Repository;

import java.util.List;


public class ResumeViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<Profile>> allProfilesList;
    public LiveData<PersonalDetails> personalDetailsLiveData;
    public LiveData<List<Qualification>> profileQualificationList;
    public LiveData<List<Skills>> profileSkillsList;
    public LiveData<List<Strengths>> profileStrengthsList;
    public LiveData<List<Projects>> profileProjectsList;
    public LiveData<List<Achievements>> profileAchievementList;
    public LiveData<List<IndustrialVisit>> profileIVList;

    public ResumeViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        allProfilesList=repository.getAllProfiles();
    }
    public void insertPersonalDetailForProfile(PersonalDetails personalDetails) {
        repository.insertPersonalDetails(personalDetails);
    }
    public void updatePersonalDetailForProfile(PersonalDetails personalDetails){
        repository.updatePersonalDetails(personalDetails);
    }
    public LiveData<PersonalDetails> getPersonalDetailForRespectiveProfile(int id) {
        personalDetailsLiveData = repository.getPersonalDetailsOfProfile(id);
        return personalDetailsLiveData;
    }
    public void insertProfile(Profile profile) {
        repository.insertProfile(profile);
    }
    public LiveData<List<Profile>> getAllProfilesList() {
        return allProfilesList;
    }


    public void insertQualificationForProfile(Qualification qualification) {
        repository.insertQualificationForProfile(qualification);
    }
    public void updateQualificationForProfile(Qualification qualification) {
        repository.updateQualificationForProfile(qualification);
    }
    public LiveData<List<Qualification>> getQualificationOfTheProfile(int qProfileId) {
        profileQualificationList = repository.getQualificationOfTheProfile(qProfileId);
        return profileQualificationList;
    }

    public void insertSkillsForProfile(Skills skills) {
        repository.insertSkillsForProfile(skills);
    }
    public void updateskillsForProfile(Skills skills) {
        repository.updateSkillsForProfile(skills);
    }
    public LiveData<List<Skills>> getskillsOfTheProfile(int sProfileId) {
        profileSkillsList = repository.getSkillsOfTheProfile(sProfileId);
        return profileSkillsList;
    }
    public void insertStrengthForProfile(Strengths strengths){
        repository.insertStrengthForProfile(strengths);
    }
    public void updateStrengthForProfile(Strengths strengths) {
        repository.updateStrengthForProfile(strengths);
    }
    public LiveData<List<Strengths>> getStrengthOfTheProfile(int strProfileId) {
        profileStrengthsList = repository.getStrengthsOfTheProfile(strProfileId);
        return profileStrengthsList;
    }
    public void insertProjectForProfile(Projects project){
        repository.insertProjectForProfile(project);
    }
    public void updateProjectForProfile(Projects project) {
        repository.updateProjectForProfile(project);
    }
    public LiveData<List<Projects>> getProjectsOfTheProfile(int projectProfileId) {
        profileProjectsList = repository.getProjectsOfTheProfile(projectProfileId);
        return profileProjectsList;
    }
    public void insertAchievementForProfile(Achievements achievements){
        repository.insertAchievementForProfile(achievements);
    }
    public void updateAchievementForProfile(Achievements achievements) {
        repository.updateAchievementForProfile(achievements);
    }
    public LiveData<List<Achievements>> getAchievementOfTheProfile(int achievementProfileId) {
        profileAchievementList = repository.getAchievementsOfTheProfile(achievementProfileId);
        return profileAchievementList;
    }
    public void insertIVForProfile(IndustrialVisit industrialVisit){
        repository.insertIVForProfile(industrialVisit);
    }
    public void updateIVForProfile(IndustrialVisit industrialVisit) {
        repository.updateIVForProfile(industrialVisit);
    }
    public LiveData<List<IndustrialVisit>> getIVOfTheProfile(int IVProfileId) {
        profileIVList = repository.getIVOfTheProfile(IVProfileId);
        return profileIVList;
    }



}
