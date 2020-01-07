package com.background.medicine.entity;

import javax.persistence.*;


@Entity
@Table(name="filecomment")
public class filecomment {
    @Id
    @GeneratedValue
    @Column(name="CommentID")
    public int CommentID;
    @Column(name="fileID")
    public int fileID;
    @Column(name="userID")
    public int userID;
    @Column(name="commentInfo")
    public String commentInfo;
    @Column(name="commentTime")
    public String commentTime;

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
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

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "filecomment{" +
                "CommentID=" + CommentID +
                ", fileID=" + fileID +
                ", userID=" + userID +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentTime='" + commentTime + '\'' +
                '}';
    }
}
