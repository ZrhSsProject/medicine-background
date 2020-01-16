package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fileinfo")
@Getter
@Setter
@ToString
public class fileinfo {
    @Id
    @Column(name="fileID")
    public int fileID;
    @Column(name="list")
    public String list;
    @Column(name="introduce")
    public String introduce;


}
