package com.background.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fileinfo")
public class fileinfo {
    @Id
    @Column(name="fileID")
    public int fileID;
    @Column(name="list")
    public String list;
    @Column(name="introduce")
    public String introduce;

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "fileinfo{" +
                "fileID=" + fileID +
                ", list='" + list + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
