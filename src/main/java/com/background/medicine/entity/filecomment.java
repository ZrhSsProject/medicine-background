package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name="filecomment")
@Getter
@Setter
@ToString
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
}
