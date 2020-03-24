package com.background.medicine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class searchView {
    String filename;
    Map<Integer,String> author;
    List<Map<Integer,String>> relate;
}
