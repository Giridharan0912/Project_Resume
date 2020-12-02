package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Profile.class, parentColumns = "profileId", childColumns = "strProfileId")
        , indices = @Index(value = "strProfileId", unique = false))
public class Strengths {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int strengthId;
    public int strProfileId;
    public String strength;

    public Strengths(int strProfileId, String strength) {
        this.strProfileId = strProfileId;
        this.strength = strength;
    }

    @Ignore
    public Strengths(int strengthId, int strProfileId, String strength) {
        this.strengthId = strengthId;
        this.strProfileId = strProfileId;
        this.strength = strength;
    }

    public void setStrengthId(int strengthId) {
        this.strengthId = strengthId;
    }

    public void setStrProfileId(int strProfileId) {
        this.strProfileId = strProfileId;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getStrengthId() {
        return strengthId;
    }

    public int getStrProfileId() {
        return strProfileId;
    }

    public String getStrength() {
        return strength;
    }
}
