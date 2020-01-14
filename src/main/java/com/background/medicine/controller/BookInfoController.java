package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.fileinfoDao;
import com.background.medicine.entity.fileinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class BookInfoController {

    @Autowired
    fileinfoDao fileinfoDao;

    @RequestMapping(value = "BookDetail/{fileID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookdetail(@PathVariable int fileID){//获取用户传入url的信息，配置要求，url路径变量

        fileinfo fileinfo = fileinfoDao.findByFileID(fileID);
        Object obj = JSONArray.toJSON(fileinfo);
        String json = "BookDetailHandler("+obj.toString()+");";
        return json;
    }
}
