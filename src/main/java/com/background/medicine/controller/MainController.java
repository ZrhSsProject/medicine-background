package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.FileDao;
import com.background.medicine.dao.fileinfoDao;
import com.background.medicine.entity.Result;
import com.background.medicine.entity.Users;
import com.background.medicine.entity.file;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.background.medicine.controller.ReadByLine.readFile;

@RestController
@RequestMapping("")
public class MainController {
    @Autowired
    FileDao fileDao;

    @RequestMapping(value = "Main/{start}/{num}",method = RequestMethod.GET)
    @ResponseBody
    public String findAll(@PathVariable int start,@PathVariable int num){
        List<file> file = fileDao.findAll(start,num);
        Object obj = JSONArray.toJSON(file);
        String json = "MainHandler("+obj.toString()+");";
        return json;
    }


    @RequestMapping(value = "simpleSearch/{select}/{querydata}",method = RequestMethod.GET)
    @ResponseBody
    public String simpleSearch(@PathVariable String select,@PathVariable String querydata) {
        List<file> file =null;
        if(select.equals("书名")){
            file= fileDao.findByNames(querydata);}
        else if(select.equals("作者")){
            file= fileDao.findByAuthor(querydata);
        }
        Object obj= JSONArray.toJSON(file);
        String json = "simplesearchHandler(" + obj.toString() + ");";
        return json;

    }

    @RequestMapping(value = "maintest",method = RequestMethod.GET)
    @ResponseBody
    public String findAlltest(){
        List<file> file = fileDao.findAll();
        Object obj = JSONArray.toJSON(file);
        String json = "MaintestHandler("+obj.toString()+");";
        return json;
    }


    @RequestMapping(value = "bookread/{bookid}/{page}",method = RequestMethod.GET)
    @ResponseBody
    public String bookread(@PathVariable int bookid,@PathVariable int page) {
        String path = fileDao.findByFileID(bookid).getFileLocation();
        String res = readFile(path,page);
        Result result = new Result(res);
        Object obj = JSONArray.toJSON(result);
        String json = "BookReadHandler("+obj.toString()+");";
        return json;
    }
}


