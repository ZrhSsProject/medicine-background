package com.background.medicine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//这里是和数据库做关联映射
@Table(name = "dayclick")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class dayclick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clickID")
    public int clickID;
    @Column(name = "count")
    public int count;
    @Column(name = "day")
    public String day;

    public dayclick(){

    }

    public dayclick(int count, String day) {
        this.count = count;
        this.day = day;
    }
}
