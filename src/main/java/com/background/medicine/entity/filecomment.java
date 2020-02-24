package com.background.medicine.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="filecomment")
@Getter
@Setter
@ToString
public class filecomment {
    @Id
    @Column(name="CommentID")
    public int CommentID;
    @Column(name="fileID")
    public int fileID;
    @Column(name="userID")
    public int userID;
    @Column(name="title")
    public String title;
    @Column(name="content")
    public String content;
    @Column(name="commentTime")
    public String commentTime;

    @Column(name="fileName")
    public String fileName = "";

    @Column(name="userName")
    public String userName = "";

    public filecomment(){

    }

    public filecomment(int fileID, int userID, String title, String content, String commentTime, String fileName,String userName
    ) {
        this.fileID = fileID;
        this.userID = userID;
        this.title = title;
        this.content = content;
        this.commentTime = commentTime;
        this.fileName = fileName;
        this.userName = userName;
    }
}
