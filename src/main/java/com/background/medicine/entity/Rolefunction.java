package com.background.medicine.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="rolefunction")
@Entity
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

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getFunctionID() {
        return functionID;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

    @Override
    public String toString() {
        return "Rolefunction{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", functionID=" + functionID +
                '}';
    }
}
