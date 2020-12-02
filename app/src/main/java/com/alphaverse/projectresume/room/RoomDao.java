package com.alphaverse.projectresume.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.PersonalDetails;
import com.alphaverse.projectresume.model.Profile;
import com.alphaverse.projectresume.model.Projects;
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert
    void insertProfile(Profile profile);

    @Query("SELECT * FROM Profile ORDER BY profileName ASC")
    LiveData<List<Profile>> getAllProfiles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPersonalDetails(PersonalDetails personalDetails);

    @Query("SELECT * FROM PersonalDetails  WHERE profileId= :id LIMIT 1")
    LiveData<PersonalDetails> getPersonalDetailsOfProfile(int id);

    @Update
    int updatePersonalDetails(PersonalDetails personalDetails);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQualification(Qualification qualification);

    @Query("SELECT * FROM Qualification WHERE QProfileId=:id")
    LiveData<List<Qualification>> getQualificationForProfile(int id);

    @Update
    int updateQualification(Qualification qualification);

    @Insert
    void insertSkill(Skills skills);

    @Query("SELECT * FROM Skills WHERE sProfileId=:id")
    LiveData<List<Skills>> getSkillForProfile(int id);

    @Update
    int updateSkills(Skills skills);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStrength(Strengths strengths);

    @Query("SELECT * FROM Strengths WHERE strProfileId=:id")
    LiveData<List<Strengths>> getStrengthForProfile(int id);

    @Update
    int updateStrength(Strengths strengths);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(Projects project);

    @Query("SELECT * FROM Projects WHERE projectProfileId=:id")
    LiveData<List<Projects>> getProjectForProfile(int id);

    @Update
    int updateProject(Projects project);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAchievement(Achievements achievements);

    @Update
    int updateAchievement(Achievements achievements);

    @Query("SELECT * FROM Achievements WHERE achievementProfileId=:id")
    LiveData<List<Achievements>> getAchievementForProfile(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIV(IndustrialVisit industrialVisit);

    @Update
    int updateIV(IndustrialVisit industrialVisit);

    @Query("SELECT * FROM IndustrialVisit WHERE IVProfileId=:id")
    LiveData<List<IndustrialVisit>> getIVForProfile(int id);
}
