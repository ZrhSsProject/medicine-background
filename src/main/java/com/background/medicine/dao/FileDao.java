package com.background.medicine.dao;

import com.background.medicine.entity.file;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//这里是配置sql语句
@Repository
@Transactional
public interface FileDao extends JpaRepository<file,Long> {
    List<file> findByFileIDIsNotNull();

    file findByFileID(int fileID);

    @Query(nativeQuery=true,value = "select * from file  limit ?1, ?2")
    List<file> findAll(int start,int num);

    @Query("select fileName,author,cateName,edition,bookNumber from file where fileName =?1 ")
    List<file> findByFileName(String fileName);

    @Query("select f from file f where fileName = ?1")
    List<file> findByNames(String querydata);

    @Query("select f from file f where author = ?1")
    List<file> findByAuthor(String querydata);

    @Query(nativeQuery=true,value = "select * from file")
    List<file> findAlltest();

    //根据用户查看信息
    @Query(nativeQuery=true,value = "select * from file where cateName = (select cateID from filecategory " +
            "where cateName = ?1) limit ?2, ?3")
    List<file> findByCate(String cateName, int start,int num);

}
