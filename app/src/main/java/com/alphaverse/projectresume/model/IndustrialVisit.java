package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(foreignKeys = @ForeignKey(entity = Profile.class, parentColumns = "profileId", childColumns = "IVProfileId")
        , indices = @Index(value = "IVProfileId", unique = false))
public class IndustrialVisit {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public  int IVId;
    public int IVProfileId;
    public String IV;
@Ignore
    public IndustrialVisit(int IVId, int IVProfileId, String IV) {
        this.IVId = IVId;
        this.IVProfileId = IVProfileId;
        this.IV = IV;
    }

    public IndustrialVisit(int IVProfileId, String IV) {
        this.IVProfileId = IVProfileId;
        this.IV = IV;
    }

    public int getIVId() {
        return IVId;
    }

    public int getIVProfileId() {
        return IVProfileId;
    }

    public String getIV() {
        return IV;
    }
}
