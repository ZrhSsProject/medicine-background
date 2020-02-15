package com.background.medicine.dto;

import com.background.medicine.entity.file;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class fileCount {
    List<file> file = null;
    int count = 0;

}
