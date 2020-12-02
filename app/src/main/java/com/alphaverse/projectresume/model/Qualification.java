package com.alphaverse.projectresume.model;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Profile.class, parentColumns = "profileId", childColumns = "QProfileId")
        , indices = @Index(value = "QProfileId", unique = false))
public class Qualification {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int qualificationId;
    public int QProfileId;
    @NonNull
    public String degree;
    @NonNull
    public String institution;
    @NonNull
    public String YOP;
    @NonNull
    public String percentage;

    @Ignore
    public Qualification(@NonNull int qualificationId, @NonNull int QProfileId, @NonNull String degree, @NonNull String institution, @NonNull String YOP, @NonNull String percentage) {
        this.qualificationId = qualificationId;
        this.QProfileId = QProfileId;
        this.degree = degree;
        this.institution = institution;
        this.YOP = YOP;
        this.percentage = percentage;
    }

    public Qualification(@NonNull int QProfileId, @NonNull String degree, @NonNull String institution, @NonNull String YOP, @NonNull String percentage) {
        this.QProfileId = QProfileId;
        this.degree = degree;
        this.institution = institution;
        this.YOP = YOP;
        this.percentage = percentage;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public int getQProfileId() {
        return QProfileId;
    }

    public void setQProfileId(int QProfileId) {
        this.QProfileId = QProfileId;
    }

    public String getDegree() {
        return degree;
    }

    public String getInstitution() {
        return institution;
    }

    public String getYOP() {
        return YOP;
    }

    public String getPercentage() {
        return percentage;
    }


}
