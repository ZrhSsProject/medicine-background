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

    @Query(nativeQuery=true,value = "select * from file where " +
            "(fileName like ?1 or author like ?1) limit ?2, ?3")
    List<file> findAll(String search, int start,int num);

    @Query(nativeQuery=true,value = "select count(1) from file where " +
            "(fileName like ?1 or author like ?1)")
    int countAll(String search);

    @Query("select fileName,author,cateName,edition,bookNumber from file where fileName =?1 ")
    List<file> findByFileName(String fileName);

    @Query(nativeQuery=true,value = "select * from file f where fileName like ?1 limit ?2, ?3")
    List<file> findByNames(String querydata,int start,int num);

    @Query(nativeQuery=true,value = "select count(1) from file f where fileName = ?1")
    int countByName(String querydata);

    @Query(nativeQuery=true,value = "select * from file f where author like ?1 limit ?2, ?3")
    List<file> findByAuthor(String querydata,int start,int num);

    @Query(nativeQuery=true,value = "select count(1) from file f where author = ?1")
    int countByAuthor(String querydata);

    @Query(nativeQuery=true,value = "select * from file")
    List<file> findAlltest();

    //根据用户查看信息
    @Query(nativeQuery=true,value = "select * from file where cateName = ?1 and " +
            "(fileName like ?2 or author like ?2) limit ?3, ?4")
    List<file> findByCate(String cateName, String search, int start,int num);

    @Query(nativeQuery=true,value = "select count(1) from file where cateName = ?1 and " +
            "(fileName like ?2 or author like ?2)")
    int countByCate(String cateName, String search);

    @Query(nativeQuery=true,value = "select * from file where dynasty = ?1 and " +
            "(fileName like ?2 or author like ?2) limit ?3, ?4")
    List<file> findByDynasty(String dynasty, String search, int start,int num);

    @Query(nativeQuery=true,value = "select count(1) from file where dynasty = ?1 and " +
            "(fileName like ?2 or author like ?2)")
    int countByDynasty(String dynasty, String search);

    @Query(nativeQuery=true,value = "select * from file where cateName = ?1 and dynasty = ?2 and " +
            "(fileName like ?3 or author like ?3) limit ?4, ?5")
    List<file> findByCateAndDynasty(String cateName, String dynasty, String search, int start,int num);

    @Query(nativeQuery=true,value = "select count(1) from file where cateName = ?1 and dynasty = ?2 and " +
            "(fileName like ?3 or author like ?3")
    int countByCateAndDynasty(String cateName, String dynasty, String search);

//  高级检索
    @Query(nativeQuery=true,value = "select f.* from file f join filedynasty d on f.dynasty=d.dynasty where fileName like ?1 and author like ?2 " +
            "and translator like ?3 and bookNumber like ?4 and cateName like ?5 and press like ?6 " +
            "and value >= ?7 and value <= ?8  limit ?9, ?10")
    List<file> advancedSearch(String fileName, String author, String translator, String bookNumber,
                                    String cateName, String press, int age1, int age2, int start, int num);

//  检索音乐
    @Query(nativeQuery=true,value = "select count(1) from file f join filedynasty d on f.dynasty=d.dynasty where fileName like ?1 and author like ?2 " +
            "and translator like ?3 and bookNumber like ?4 and cateName like ?5 and press like ?6 " +
            "and value >= ?7 and value <= ?8")
    int countAdvanced(String fileName, String author, String translator, String bookNumber,
                              String cateName, String press, int age1, int age2);

}
