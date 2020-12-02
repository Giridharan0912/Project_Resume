package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Profile.class, parentColumns = "profileId", childColumns = "sProfileId")
        , indices = @Index(value = "sProfileId", unique = false))
public class Skills {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public  int skillId;
    public int sProfileId;
    public String skill;
    @Ignore
    public Skills() {
    }

    public Skills(@NonNull int sProfileId, @NonNull String skill) {
        this.sProfileId = sProfileId;
        this.skill = skill;
    }
    @Ignore
    public Skills(@NonNull int skillId,@NonNull int sProfileId,@NonNull String skill) {
        this.skillId = skillId;
        this.sProfileId = sProfileId;
        this.skill = skill;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getsProfileId() {
        return sProfileId;
    }

    public void setsProfileId(int sProfileId) {
        this.sProfileId = sProfileId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
