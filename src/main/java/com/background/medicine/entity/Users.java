package com.background.medicine.entity;

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
public class Users {
    @Id
    @GeneratedValue
//    主键自增长设置
    @Column(name = "userID")
    public int userID;
    @Column(name = "userName")
    public String userName;
    @Column(name = "password")
    public String password;
    @Column(name = "roleID")
    public int roleID;
    @Column(name = "department")
    public String department;
}
