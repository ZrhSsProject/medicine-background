package com.background.medicine.dao;

import com.background.medicine.entity.todayhot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface todayhotDao extends JpaRepository<todayhot,Long> {
    @Query(nativeQuery=true,value = "SELECT * FROM todayhot where day=?1 order by count desc limit 0,7")
    List<todayhot> findday(String day);

    @Query(nativeQuery=true,value = "SELECT count(1) FROM todayhot where fileID = ?1 and day=?2")
    int countday(int fileID, String day);

    @Query(nativeQuery=true,value = "update todayhot set count=count+1 where fileID = ?1 and day=?2")
    @Modifying
    int updateday(int fileID, String day);
}
