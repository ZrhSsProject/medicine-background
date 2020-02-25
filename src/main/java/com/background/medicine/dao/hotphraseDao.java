package com.background.medicine.dao;

import com.background.medicine.entity.hotphrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface hotphraseDao extends JpaRepository<hotphrase,Long> {
    @Query(nativeQuery=true,value = "SELECT * FROM hotphrase where day=?1 order by count desc limit 0,7")
    List<hotphrase> findday(String day);

    @Query(nativeQuery=true,value = "SELECT count(1) FROM hotphrase where phrase = ?1 and day=?2")
    int countday(String phrase, String day);

    @Query(nativeQuery=true,value = "update hotphrase set count=count+1 where phrase = ?1 and day=?2")
    @Modifying
    int updateday(String phrase, String day);
}
