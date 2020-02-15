package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.filecommentDao;
import com.background.medicine.dao.fileinfoDao;
import com.background.medicine.entity.Result;
import com.background.medicine.entity.filecomment;
import com.background.medicine.entity.fileinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class BookInfoController {

    @Autowired
    fileinfoDao fileinfoDao;

    @Autowired
    filecommentDao filecommentDao;



    @RequestMapping(value = "BookDetail/{fileID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookdetail(@PathVariable int fileID){//获取用户传入url的信息，配置要求，url路径变量

        fileinfo fileinfo = fileinfoDao.findByFileID(fileID);
        Object obj = JSONArray.toJSON(fileinfo);
        String json = "BookDetailHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "CommentAdd/{fileID}/{userID}/{title}/{content}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commenadd(@PathVariable int fileID,@PathVariable int userID,
                            @PathVariable String title,@PathVariable String content){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(new Date());
        filecomment filecomment = new filecomment(fileID,userID,title,content,now);
        filecomment res = filecommentDao.save(filecomment);
        Object obj = JSONArray.toJSON(res);
        String json = "CommentAddHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "CommentGet/{fileID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commentget(@PathVariable int fileID){
        List<filecomment> file = filecommentDao.findByFileID(fileID);
        Object obj = JSONArray.toJSON(file);
        String json = "CommentAddHandler("+obj.toString()+");";
        return json;
    }
}
