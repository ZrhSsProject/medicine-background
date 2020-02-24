package com.background.medicine.dao;

import com.background.medicine.entity.file;
import com.background.medicine.entity.filecomment;
import com.background.medicine.entity.mybooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//这里是配置sql语句
@Repository
@Transactional
public interface mybooksDao extends JpaRepository<mybooks,Long> {
    List<mybooks> findByFileIDIsNotNull();

    @Query(nativeQuery=true,value = "select count(1) from mybooks where " +
            "fileID = ?1 and userID = ?2")
    int GetByFileIDAndUserID(int fileID,int userID);

    @Query(nativeQuery=true,value = "select * from mybooks where userID = ?1")
    List<mybooks> getByuserID(int userID);

    @Query(nativeQuery=true,value = "select * from mybooks where userID = ?1 " +
            "limit ?2, ?3")
    List<mybooks> findByUserID(int userID, int start, int num);

    @Query(nativeQuery=true,value = "select count(1) from mybooks where userID = ?1 ")
    int countByUser(int userID);

    @Query(nativeQuery=true,value = "delete from mybooks where fileID = ?1 and userID = ?2")
    @Modifying
    int deleteByfileIDAnduserID(int fileID, int userID);
}
