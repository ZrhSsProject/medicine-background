package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.FileDao;
import com.background.medicine.entity.BookPage;
import com.background.medicine.entity.file;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.background.medicine.controller.ReadBook.*;
import static com.background.medicine.controller.ReadByLine.readFile;
import static com.background.medicine.controller.ReadByLine.readLine;


@RestController
@RequestMapping("")
public class MainController {
    @Autowired
    FileDao fileDao;

    @RequestMapping(value = "Main/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String findAll(@PathVariable int start,@PathVariable int num){
        List<file> file = fileDao.findAll(start,num);
        Object obj = JSONArray.toJSON(file);
        String json = "MainHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "Cate/{cateName}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String findByCate(@PathVariable String cateName,@PathVariable int start,@PathVariable int num){
        List<file> file = fileDao.findByCate(cateName,start,num);
        Object obj = JSONArray.toJSON(file);
        String json = "CateHandler("+obj.toString()+");";
        return json;
    }


    @RequestMapping(value = "simpleSearch/{select}/{querydata}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
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

    //indextest中的datatable应用
//    @RequestMapping(value = "maintest",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
//    @ResponseBody
//    public String findAlltest(){
//        List<file> file = fileDao.findAll();
//        Object obj = JSONArray.toJSON(file);
//        String json = "MaintestHandler("+obj.toString()+");";
//        return json;
//    }


//    @RequestMapping(value = "bookread/{bookid}/{page}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
//    @ResponseBody
//    public String bookread(@PathVariable int bookid,@PathVariable int page) {
//        String path = fileDao.findByFileID(bookid).getFileLocation();
//        String res = readFile(path,page);
//        int num = readLine(path);//Redis扩展
//        BookPage bookPage = new BookPage(res,null,num);
//        Object obj = JSONArray.toJSON(bookPage);
//        String json = "BookReadHandler("+obj.toString()+");";
//        return json;
//    }

    //produces="application/json;charset=UTF-8"  处理返回的json中文乱码
    @RequestMapping(value = "bookhandle/{bookid}/{page}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookhandle(@PathVariable int bookid,@PathVariable int page) {
        String path = fileDao.findByFileID(bookid).getFileLocation();
        String res = readTxtFile(path+"\\txt\\",page);
        String url = readTIF(path+"\\tif\\",page);
        int num = readNum(path+"\\txt\\");//Redis扩展
        BookPage bookPage = new BookPage(res,url,num);
        Object obj = JSONArray.toJSON(bookPage);
        String json = "BookHandleHandler("+obj.toString()+");";
        return json;
    }
}


