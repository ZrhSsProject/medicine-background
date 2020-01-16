package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="fileannotation")
@Getter
@Setter
@ToString
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
}
