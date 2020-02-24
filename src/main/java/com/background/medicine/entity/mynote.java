package com.background.medicine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="mynote")
@Getter
@Setter
@ToString
public class mynote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="noteID")
    public int noteID;

    @Column(name="fileID")
    public int fileID=-1;
    @Column(name="fileName")
    public String fileName;
    @Column(name="userID")
    public int userID;
    @Column(name="origin")
    public String origin;
    @Column(name="content")
    public String content;

    public mynote(){

    }

    public mynote(int fileID, String fileName, int userID, String origin, String content) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.userID = userID;
        this.origin = origin;
        this.content = content;
    }
}
