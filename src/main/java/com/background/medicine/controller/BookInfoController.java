package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.*;
import com.background.medicine.dto.onefilecommentCount;
import com.background.medicine.entity.*;
import com.background.medicine.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class BookInfoController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    FileDao fileDao;

    @Autowired
    fileinfoDao fileinfoDao;

    @Autowired
    filecommentDao filecommentDao;

    @Autowired
    mybooksDao mybooksDao;

    @Autowired
    mynoteDao mynoteDao;

    @RequestMapping(value = "BookDetail/{fileID}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookdetail(@PathVariable int fileID,@PathVariable int userID) {//获取用户传入url的信息，配置要求，url路径变量

        fileinfo fileinfo = fileinfoDao.findByFileID(fileID);
        List<filecomment> filecomment = filecommentDao.findByFileID(fileID);
        int count =0;
        if(redisUtil.get(fileID+"Book") != null){
            count = (int) redisUtil.get(fileID+"Book");
        }else {
            count = filecommentDao.countByFileID(fileID);
        }
        List<mynote> mynotes = mynoteDao.findByUserIDAndFileID(userID,fileID);
        onefilecommentCount oc = new onefilecommentCount(filecomment,mynotes,fileinfo,count);

        Object obj = JSONArray.toJSON(oc);
        String json = "BookDetailHandler(" + obj.toString() + ");";
        return json;
    }
}
