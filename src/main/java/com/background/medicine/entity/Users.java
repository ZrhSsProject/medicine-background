package com.background.medicine.entity;

import javax.persistence.*;

//这里是和数据库做关联映射
@Table(name = "users")
@Entity
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


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roleID=" + roleID +
                ", department='" + department + '\'' +
                '}';
    }
}
