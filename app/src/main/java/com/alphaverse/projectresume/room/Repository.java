package com.alphaverse.projectresume.room;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.PersonalDetails;
import com.alphaverse.projectresume.model.Profile;
import com.alphaverse.projectresume.model.Projects;
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Repository {
    private static final String TAG = "Repository";
    public RoomDb roomDb;
    public RoomDao roomDao;
    public LiveData<List<Profile>> profileListLivedata;
    public LiveData<PersonalDetails> personalDetailsOfProfile;
    public LiveData<List<Qualification>> profileWithQualificationLiveData;
    public LiveData<List<Skills>> profileWithSkillsLiveData;
    public LiveData<List<Strengths>> profileWithStrengthsLiveData;
    public LiveData<List<Projects>> profileWithProjectsLiveData;
    public LiveData<List<Achievements>> profileWithAchievementsLiveData;
    public LiveData<List<IndustrialVisit>> profileWithIVLiveData;
    private Context context;

    public Repository() {
    }

    public Repository(Application application) {
        roomDb = RoomDb.getDatabase(application);
        roomDao = roomDb.roomDao();
        profileListLivedata = roomDao.getAllProfiles();
        this.context = application.getApplicationContext();
    }

    public void insertProfile(Profile profile) {
        Completable.fromAction(() -> roomDao.insertProfile(profile)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());

            }
        });
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return profileListLivedata;
    }

    public void insertPersonalDetails(PersonalDetails personalDetails){
        Completable.fromAction(() ->
                roomDao.insertPersonalDetails(personalDetails)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updatePersonalDetails(PersonalDetails personalDetails){
        Completable.fromAction(() ->
                roomDao.updatePersonalDetails(personalDetails)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }


    public LiveData<PersonalDetails> getPersonalDetailsOfProfile(int profileId) {
        personalDetailsOfProfile = roomDao.getPersonalDetailsOfProfile(profileId);
        return personalDetailsOfProfile;
    }

    public void insertQualificationForProfile(Qualification qualification){
        Completable.fromAction(() ->
                roomDao.insertQualification(qualification)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updateQualificationForProfile(Qualification qualification){
        Completable.fromAction(() ->
                roomDao.updateQualification(qualification)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }

    public LiveData<List<Qualification>> getQualificationOfTheProfile(int qProfileId) {
        profileWithQualificationLiveData = roomDao.getQualificationForProfile(qProfileId);
        return profileWithQualificationLiveData;
    }

    public void insertSkillsForProfile(Skills skills){
        Completable.fromAction(() ->
                roomDao.insertSkill(skills)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updateSkillsForProfile(Skills skills){
        Completable.fromAction(() ->
                roomDao.updateSkills(skills)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public LiveData<List<Skills>> getSkillsOfTheProfile(int sProfileId) {
        profileWithSkillsLiveData = roomDao.getSkillForProfile(sProfileId);
        return profileWithSkillsLiveData;
    }
    public void insertStrengthForProfile(Strengths strengths){
        Completable.fromAction(() ->
                roomDao.insertStrength(strengths)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updateStrengthForProfile(Strengths strengths){
        Completable.fromAction(() ->
                roomDao.updateStrength(strengths)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }

    public LiveData<List<Strengths>> getStrengthsOfTheProfile(int strProfileId) {
        profileWithStrengthsLiveData = roomDao.getStrengthForProfile(strProfileId);
        return profileWithStrengthsLiveData;
    }
    public void insertProjectForProfile(Projects project){
        Completable.fromAction(() ->
                roomDao.insertProject(project)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updateProjectForProfile(Projects project){
        Completable.fromAction(() ->
                roomDao.updateProject(project)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public LiveData<List<Projects>> getProjectsOfTheProfile(int projectProfileId) {
        profileWithProjectsLiveData = roomDao.getProjectForProfile(projectProfileId);
        return profileWithProjectsLiveData;
    }
    public void insertAchievementForProfile(Achievements achievements){
        Completable.fromAction(() ->
                roomDao.insertAchievement(achievements)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updateAchievementForProfile(Achievements achievements){
        Completable.fromAction(() ->
                roomDao.updateAchievement(achievements)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public LiveData<List<Achievements>> getAchievementsOfTheProfile(int achievementsProfileId) {
        profileWithAchievementsLiveData = roomDao.getAchievementForProfile(achievementsProfileId);
        return profileWithAchievementsLiveData;
    }
    public void insertIVForProfile(IndustrialVisit industrialVisit){
        Completable.fromAction(() ->
                roomDao.insertIV(industrialVisit)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public void updateIVForProfile(IndustrialVisit industrialVisit){
        Completable.fromAction(() ->
                roomDao.updateIV(industrialVisit)).observeOn(Schedulers.io()).subscribeOn(Schedulers.single()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }
    public LiveData<List<IndustrialVisit>> getIVOfTheProfile(int IVProfileId) {
        profileWithIVLiveData = roomDao.getIVForProfile(IVProfileId);
        return profileWithIVLiveData;
    }


}
