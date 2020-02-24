package com.background.medicine.dto;

import com.background.medicine.entity.file;
import com.background.medicine.entity.mybooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class bookshelfCount {
    List<mybooks> mybooks = null;
    int count = 0;
}
