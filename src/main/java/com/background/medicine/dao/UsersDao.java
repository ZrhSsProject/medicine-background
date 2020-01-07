package com.background.medicine.dao;

import com.background.medicine.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//这里是配置sql语句
@Repository
@Transactional
public interface UsersDao extends JpaRepository<Users,Long> {
    //根据用户名查找信息
    Users findByUserName(String username);
    //根据部门查找用户
    Users findByDepartment(String department);
    //username+password
    Users findByUserNameAndPassword(String username,String password);
    //自定制sql
    @Query("select userName from Users where userName = ?1")
    List<String> findByNames(String userName);
}
