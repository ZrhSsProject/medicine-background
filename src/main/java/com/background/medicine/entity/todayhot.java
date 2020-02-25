package com.background.medicine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//这里是和数据库做关联映射
@Table(name = "todayhot")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class todayhot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotID")
    public int hotID;
    @Column(name = "fileID")
    public int fileID;
    @Column(name = "fileName")
    public String fileName;
    @Column(name = "count")
    public int count;
    @Column(name = "day")
    public String day;

    public todayhot(){

    }

    public todayhot(int fileID, String fileName, int count, String day) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.count = count;
        this.day = day;
    }
}
