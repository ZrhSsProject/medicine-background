package com.background.medicine.entity;

import javax.persistence.*;

@Entity
@Table(name="rdf")
public class rdf {
    @Id
    @Column(name="fileID")
    public int fileID;
    @Column(name="subject")
    public String subject;
    @Column(name="predicate")
    public String predicate;
    @Column(name="object")
    public String object;

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "rdf{" +
                "fileID=" + fileID +
                ", subject='" + subject + '\'' +
                ", predicate='" + predicate + '\'' +
                ", object='" + object + '\'' +
                '}';
    }
}
