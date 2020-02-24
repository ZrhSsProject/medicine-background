package com.background.medicine.dto;

import com.background.medicine.entity.mynote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class noteCount {
    public List<mynote> mynote;
    public int count;
}
