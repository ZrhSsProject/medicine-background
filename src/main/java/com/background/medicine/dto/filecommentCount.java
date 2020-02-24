package com.background.medicine.dto;

import com.background.medicine.entity.filecomment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class filecommentCount {
    List<filecomment> file = null;
    int count = 0;
}
