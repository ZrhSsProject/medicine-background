package com.background.medicine.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="documentreading")
public class documentreading  implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name="fileID")
    public int fileID;
    @Id
    @Column(name="userID")
    public int userID;
    @Column(name="readingTime")
    public String readingTime;

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

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    @Override
    public String toString() {
        return "documentreading{" +
                "fileID=" + fileID +
                ", userID=" + userID +
                ", readingTime='" + readingTime + '\'' +
                '}';
    }
}
