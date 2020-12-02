package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Profile {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int profileId;

    public String profileName;



    public Profile(@NonNull String profileName) {
        this.profileName = profileName;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

}
