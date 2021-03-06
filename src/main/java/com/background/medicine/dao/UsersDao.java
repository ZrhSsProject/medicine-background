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
    @Query(nativeQuery=true,value = "select * from users limit ?1, ?2")
    List<Users> findAll(int start, int num);

    @Query(nativeQuery=true,value = "select count(1) from users")
    int countAll();

    @Query(nativeQuery=true,value = "select count(1) from users where userName = ?1")
    int countByUser(String userName);

    //根据用户名查找信息
    Users findByUserName(String username);
    //根据部门查找用户
    Users findByDepartment(String department);
    //username+password
    Users findByUserNameAndPassword(String username,String password);
    //自定制sql
    @Query("select userName from Users where userName = ?1")
    List<String> findByNames(String userName);

    @Query(nativeQuery=true,value = "delete from users where userID = ?1")
    @Modifying
    int deleteByuserID(int userID);

    @Query(nativeQuery=true,value = "update users set roleID = ?1 where userID = ?2")
    @Modifying
    int modifyByuserID(int roleID,int userID);
}
