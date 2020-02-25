package com.background.medicine.dao;

import com.background.medicine.entity.dayclick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface dayclickDao extends JpaRepository<dayclick,Long> {
    @Query(nativeQuery=true,value = "SELECT * FROM dayclick order by day desc limit 0,7")
    List<dayclick> findday();

    @Query(nativeQuery=true,value = "SELECT count(1) FROM dayclick where day = ?1")
    int countday(String day);

    @Query(nativeQuery=true,value = "update dayclick set count=count+1 where day=?1")
    @Modifying
    int updateday(String day);
}
