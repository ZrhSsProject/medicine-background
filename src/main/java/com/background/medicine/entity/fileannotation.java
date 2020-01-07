package com.background.medicine.entity;

import javax.persistence.*;

@Entity
@Table(name="fileannotation")
public class fileannotation {
    @Id
    @GeneratedValue
    @Column(name="annotationID")
    public int annotationID;
    @Column(name="fileID")
    public int fileID;
    @Column(name="userID")
    public int userID;
    @Column(name="originalSentence")
    public String originalSentence;
    @Column(name="annotationInfo")
    public String annotationInfo;

    public int getAnnotationID() {
        return annotationID;
    }

    public void setAnnotationID(int annotationID) {
        this.annotationID = annotationID;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOriginalSentence() {
        return originalSentence;
    }

    public void setOriginalSentence(String originalSentence) {
        this.originalSentence = originalSentence;
    }

    public String getAnnotationInfo() {
        return annotationInfo;
    }

    public void setAnnotationInfo(String annotationInfo) {
        this.annotationInfo = annotationInfo;
    }

    @Override
    public String toString() {
        return "fileannotation{" +
                "annotationID=" + annotationID +
                ", fileID=" + fileID +
                ", userID=" + userID +
                ", originalSentence='" + originalSentence + '\'' +
                ", annotationInfo='" + annotationInfo + '\'' +
                '}';
    }
}
