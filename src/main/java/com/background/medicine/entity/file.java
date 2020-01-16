package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="file")
@Getter
@Setter
@ToString
public class file {
    @Id
    @Column(name="fileID")
    public int fileID;
    @Column(name="fileName")
    public String fileName;
    @Column(name="author")
    public String author;
    @Column(name="cateName")
    public String cateName;
    @Column(name="translator")
    public String translator;
    @Column(name="edition")
    public String edition;
    @Column(name="bookNumber")
    public String bookNumber;
    @Column(name="originalFileLocation")
    public String originalFileLocation;
    @Column(name="fileLocation")
    public String fileLocation;
    @Column(name="readingQuantity")
    public int readingQuantity;
    @Column(name="citedQuantity")
    public int citedQuantity;
    @Column(name="press")
    public String press;
    @Column(name="dynasty")
    public String dynasty;
}
