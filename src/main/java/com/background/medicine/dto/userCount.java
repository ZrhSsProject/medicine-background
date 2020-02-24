package com.background.medicine.dto;

import com.background.medicine.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class userCount {
    List<Users> users = null;
    int count = 0;

}
