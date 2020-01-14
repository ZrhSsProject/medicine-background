package com.background.medicine.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookPage {
    String res;
    String url = null;
    int pages;
}
