package com.background.medicine.entity;

import javax.persistence.*;

@Entity
@Table(name="filecategory")
public class filecategory {
    @GeneratedValue
    @Column(name="cateID")
    public int cateID;
    @Id
    @Column(name="cateName")
    public String cateName;
    @Column(name="cateInfo")
    public String cateInfo;

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateInfo() {
        return cateInfo;
    }

    public void setCateInfo(String cateInfo) {
        this.cateInfo = cateInfo;
    }

    @Override
    public String toString() {
        return "filecategory{" +
                "cateID=" + cateID +
                ", cateName='" + cateName + '\'' +
                ", cateInfo='" + cateInfo + '\'' +
                '}';
    }
}
