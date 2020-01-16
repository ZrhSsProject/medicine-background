package com.background.medicine.dao;

import com.background.medicine.entity.fileinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface fileinfoDao extends JpaRepository<fileinfo,Long> {
    @Query(nativeQuery=true,value = "SELECT * FROM fileinfo WHERE fileID=?1")
    fileinfo findByFileID(int fileID);


    @Query(nativeQuery=true,value = "SELECT * FROM file f JOIN fileinfo fi USING (fileID) WHERE f.fileID=?1")
    List<Object[]> findAllByFileID(int fileID);



}
