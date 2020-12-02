package com.alphaverse.projectresume.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class PersonalDetails {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int personalDetailsId;
    public int profileId;
    public String fullName;
    public String emailId;
    public String phoneNumber;
    public String address;
    public String linkedIn;
    public String date;


    public PersonalDetails(int profileId, @NonNull String fullName, @NonNull String emailId, @NonNull String phoneNumber, @NonNull String address, @NonNull String linkedIn,@NonNull String date) {
        this.profileId = profileId;
        this.fullName = fullName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.linkedIn = linkedIn;
        this.date=date;
    }

    @Ignore
    public PersonalDetails(int personalDetailsId, int profileId, @NonNull String fullName, @NonNull String emailId, @NonNull String phoneNumber, @NonNull String address, @NonNull String linkedIn,@NonNull String  date) {
        this.personalDetailsId = personalDetailsId;
        this.profileId = profileId;
        this.fullName = fullName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.linkedIn = linkedIn;
        this.date=date;
    }

    public int getPersonalDetailsId() {
        return personalDetailsId;
    }

    public void setPersonalDetailsId(int personalDetailsId) {
        this.personalDetailsId = personalDetailsId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
