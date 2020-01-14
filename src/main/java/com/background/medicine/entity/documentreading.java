package com.background.medicine.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="documentreading")
@Getter
@Setter
@ToString
public class documentreading  implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name="fileID")
    public int fileID;
    @Id
    @Column(name="userID")
    public int userID;
    @Column(name="readingTime")
    public String readingTime;

}
