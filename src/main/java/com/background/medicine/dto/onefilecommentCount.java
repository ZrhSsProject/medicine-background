package com.background.medicine.dto;

import com.background.medicine.entity.filecomment;
import com.background.medicine.entity.fileinfo;
import com.background.medicine.entity.mynote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class onefilecommentCount {
    List<filecomment> filecomments;
    List<mynote> mynotes;
    fileinfo fileinfo;
    int count=0;
}
