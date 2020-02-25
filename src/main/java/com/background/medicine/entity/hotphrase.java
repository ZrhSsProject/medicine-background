package com.background.medicine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//这里是和数据库做关联映射
@Table(name = "hotphrase")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class hotphrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotID")
    public int hotID;
    @Column(name = "phrase")
    public String phrase;
    @Column(name = "count")
    public int count;
    @Column(name = "day")
    public String day;

    public hotphrase(){

    }

    public hotphrase(String phrase, int count, String day) {
        this.phrase = phrase;
        this.count = count;
        this.day = day;
    }
}
