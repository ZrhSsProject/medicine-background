package com.background.medicine.dao;

import com.background.medicine.entity.filecomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface filecommentDao extends JpaRepository<filecomment,Long> {

    List<filecomment> findByFileID(int fileID);

}
