package com.background.medicine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="rolefunction")
@Entity
@Getter
@Setter
@ToString
public class Rolefunction implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name="roleID")
    public int roleID;
    @Column(name="roleName")
    public String roleName;
    @Id
    @Column(name="functionID")
    public int functionID;
}
