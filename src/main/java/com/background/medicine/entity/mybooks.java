package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mybooks")
@Getter
@Setter
@ToString
public class mybooks {
    @Id
    @Column(name="fileID")
    public int fileID = -1;
    @Column(name="fileName")
    public String fileName;
    @Column(name="userID")
    public int userID;
}
