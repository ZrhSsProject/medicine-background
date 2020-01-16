package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="filecategory")
@Getter
@Setter
@ToString
public class filecategory {
    @GeneratedValue
    @Column(name="cateID")
    public int cateID;
    @Id
    @Column(name="cateName")
    public String cateName;
    @Column(name="cateInfo")
    public String cateInfo;

}
