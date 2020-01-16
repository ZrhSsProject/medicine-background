package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="functions")
@Getter
@Setter
@ToString
public class functions {
    @Id
    @GeneratedValue
    @Column(name="functionID")
    public int functionID;
    @Column(name="functionName")
    public String functionName;
    @Column(name="functionInfo")
    public String functionInfo;
}
