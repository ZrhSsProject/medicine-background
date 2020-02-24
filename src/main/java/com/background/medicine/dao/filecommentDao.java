package com.background.medicine.dao;

import com.background.medicine.entity.filecomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface filecommentDao extends JpaRepository<filecomment,Long> {

    List<filecomment> findByFileID(int fileID);

    @Query(nativeQuery=true,value = "select count(1) from filecomment where fileID = ?1 ")
    int countByFileID(int fileID);

    @Query(nativeQuery=true,value = "select * from filecomment where userID = ?1 " +
            "limit ?2, ?3")
    List<filecomment> findByUser(int userID, int start, int num);

    @Query(nativeQuery=true,value = "select * from filecomment limit ?1, ?2")
    List<filecomment> findAll(int start, int num);

    @Query(nativeQuery=true,value = "select count(1) from filecomment")
    int countAll();

    @Query(nativeQuery=true,value = "select count(1) from filecomment where userID = ?1 ")
    int countByUser(int userID);

    @Query(nativeQuery=true,value = "delete from filecomment where commentID = ?1 ")
    @Modifying
    int deleteBycommentID(int commentID);

    @Query(nativeQuery=true,value = "delete from filecomment where title like ?1 or content like ?1 ")
    @Modifying
    int deleteBycontent(String content);
}
