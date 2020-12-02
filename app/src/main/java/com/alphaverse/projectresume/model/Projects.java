package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Profile.class, parentColumns = "profileId", childColumns = "projectProfileId")
        , indices = @Index(value = "projectProfileId", unique = false))
public class Projects {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int projectId;
    public int projectProfileId;
    public String projectName;
    public String projectDescription;
    public String projectDuration;

    public Projects(int projectProfileId, String projectName, String projectDescription, String projectDuration) {
        this.projectProfileId = projectProfileId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectDuration = projectDuration;
    }

    @Ignore
    public Projects(int projectId, int projectProfileId, String projectName, String projectDescription, String projectDuration) {
        this.projectId = projectId;
        this.projectProfileId = projectProfileId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectDuration = projectDuration;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getProjectProfileId() {
        return projectProfileId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectDuration() {
        return projectDuration;
    }
}
