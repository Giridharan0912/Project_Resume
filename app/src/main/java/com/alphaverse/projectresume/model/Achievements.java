package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Profile.class, parentColumns = "profileId", childColumns = "achievementProfileId")
        , indices = @Index(value = "achievementProfileId", unique = false))
public class Achievements
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public  int achievementId;
    public int achievementProfileId;
    public String achievement;

    @Ignore
    public Achievements(int achievementId, int achievementProfileId, String achievement) {
        this.achievementId = achievementId;
        this.achievementProfileId = achievementProfileId;
        this.achievement = achievement;
    }

    public Achievements(int achievementProfileId, String achievement) {
        this.achievementProfileId = achievementProfileId;
        this.achievement = achievement;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public int getAchievementProfileId() {
        return achievementProfileId;
    }

    public String getAchievement() {
        return achievement;
    }
}
