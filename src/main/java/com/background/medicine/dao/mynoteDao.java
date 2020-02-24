package com.background.medicine.dao;

import com.background.medicine.entity.mynote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//这里是配置sql语句
@Repository
@Transactional
public interface mynoteDao extends JpaRepository<mynote,Long> {
    mynote findByNoteID(int noteID);

    List<mynote> findByUserIDAndFileID(int userID, int fileID);

    @Query(nativeQuery=true,value = "select * from mynote where userID = ?1 " +
            "limit ?2, ?3")
    List<mynote> findByUserID(int userID, int start, int num);

    @Query(nativeQuery=true,value = "select count(1) from mynote where userID = ?1 ")
    int countByUser(int userID);

    @Query(nativeQuery=true,value = "delete from mynote where noteID = ?1")
    @Modifying
    int deleteByfileIDAnduserID(int noteID);
}
