package com.background.medicine.entity;

import javax.persistence.*;

@Entity
@Table(name="functions")
public class functions {
    @Id
    @GeneratedValue
    @Column(name="functionID")
    public int functionID;
    @Column(name="functionName")
    public String functionName;
    @Column(name="functionInfo")
    public String functionInfo;

    public int getFunctionID() {
        return functionID;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionInfo() {
        return functionInfo;
    }

    public void setFunctionInfo(String functionInfo) {
        this.functionInfo = functionInfo;
    }

    @Override
    public String toString() {
        return "functions{" +
                "functionID=" + functionID +
                ", functionName='" + functionName + '\'' +
                ", functionInfo='" + functionInfo + '\'' +
                '}';
    }
}
