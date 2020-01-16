package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="rdf")
@Getter
@Setter
@ToString
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


}
