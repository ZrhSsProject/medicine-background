package com.background.medicine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//这里是和数据库做关联映射
@Table(name = "users")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    public int userID;
    @Column(name = "userName")
    public String userName;
    @Column(name = "password")
    public String password;
    @Column(name = "email")
    public String email;
    @Column(name = "roleID")
    public int roleID;
    @Column(name = "department")
    public String department;

    public Users(){

    }

    public Users(String userName, String password, String email, int roleID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
    }
}
