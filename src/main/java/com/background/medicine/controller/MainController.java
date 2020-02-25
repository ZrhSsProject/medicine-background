package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.FileDao;
import com.background.medicine.dao.hotphraseDao;
import com.background.medicine.dto.fileCount;
import com.background.medicine.entity.BookPage;
import com.background.medicine.entity.file;
import com.background.medicine.entity.hotphrase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.background.medicine.controller.ReadBook.*;


@RestController
@RequestMapping("")
public class MainController {
    @Autowired
    FileDao fileDao;
    
    @Autowired
    hotphraseDao hotphraseDao;

    @RequestMapping(value = "Main/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String findAll(@PathVariable int start,@PathVariable int num){
        List<file> file = fileDao.findAll("%",start,num);
        Object obj = JSONArray.toJSON(file);
        String json = "MainHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "findbook/{fileID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String findAll(@PathVariable int fileID){
        file file = fileDao.findByFileID(fileID);
        Object obj = JSONArray.toJSON(file);
        String json = "findbookHandler("+obj.toString()+");";
        return json;
    }


    @RequestMapping(value = "Cate/{cateName}/{dynasty}/{search}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String findByCategory(@PathVariable String cateName,@PathVariable String dynasty,
                                 @PathVariable String search,@PathVariable int start,@PathVariable int num){
        List<file> file =null;
        int count=50;
        if(search.equals("NULL"))
            search = "%";
        else{
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String day = sdf.format(date);
            if(hotphraseDao.countday(search,day) == 1)
                hotphraseDao.updateday(search,day);
            else
                hotphraseDao.save(new hotphrase(search,1,day));
            search = "%"+search+"%";
        }

        if(cateName.equals("全部")) {
            if (dynasty.equals("全部")){
                file = fileDao.findAll(search,start, num);
                count = fileDao.countAll(search);
            }else{
                file = fileDao.findByDynasty(dynasty, search,start, num);
                count = fileDao.countByDynasty(dynasty,search);
            }
        }else{
            if (dynasty.equals("全部")){
                file = fileDao.findByCate(cateName,search,start, num);
                count = fileDao.countByCate(cateName,search);
            }else{
                file = fileDao.findByCateAndDynasty(cateName, dynasty, search, start, num);
                count = fileDao.countByCateAndDynasty(cateName,dynasty, search);
            }
        }
        fileCount fileCount = new fileCount(file,count);
        Object obj = JSONArray.toJSON(fileCount);
        String json = "CateHandler("+obj.toString()+");";
        return json;
    }


    @RequestMapping(value = "simpleSearch/{select}/{querydata}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String simpleSearch(@PathVariable String select,@PathVariable String querydata,@PathVariable int start,@PathVariable int num) {
        List<file> file =null;
        int count= 0;

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String day = sdf.format(date);
        if(hotphraseDao.countday(querydata,day) == 1)
            hotphraseDao.updateday(querydata,day);
        else
            hotphraseDao.save(new hotphrase(querydata,1,day));
        
        if(select.equals("书名")){
            count = fileDao.countByName(querydata);
            file= fileDao.findByNames("%"+querydata+"%",start,num);}
        else if(select.equals("作者")){
            count = fileDao.countByAuthor(querydata);
            file= fileDao.findByAuthor("%"+querydata+"%",start,num);
        }else if(select.equals("全部")){
            file = fileDao.findAll("%"+querydata+"%",start,num);
            count = fileDao.countAll("%"+querydata+"%");
        }
        fileCount fileCount = new fileCount(file,count);
        Object obj= JSONArray.toJSON(fileCount);
        String json = "simplesearchHandler(" + obj.toString() + ");";
        return json;

    }

    @RequestMapping(value = "advancedSearch/{fileName}/{author}/{translator}/{bookNumber}/{cateName}/{press}/{age1}/{age2}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String advancedSearch(@PathVariable String fileName, @PathVariable String author, @PathVariable String translator, @PathVariable String bookNumber,
                               @PathVariable String cateName, @PathVariable String press, @PathVariable int age1, @PathVariable int age2, @PathVariable int start, @PathVariable int num){
        List<file> file =null;
        int count= 0;

        fileName = fileName.equals("NULL") ? "%%" : "%"+fileName+"%";
        author = author.equals("NULL") ? "%%" : "%"+author+"%";
        translator = translator.equals("NULL") ? "%%" : "%"+translator+"%";
        bookNumber = bookNumber.equals("NULL") ? "%%" : "%"+bookNumber+"%";
        cateName = cateName.equals("NULL") ? "%%" : "%"+cateName+"%";
        press = press.equals("NULL") ? "%%" : "%"+press+"%";

        file = fileDao.advancedSearch(fileName,author,translator,bookNumber,cateName,press,age1,age2,start,num);
        count = fileDao.countAdvanced(fileName,author,translator,bookNumber,cateName,press,age1,age2);

        fileCount fileCount = new fileCount(file,count);
        Object obj= JSONArray.toJSON(fileCount);
        String json = "advancedsearchHandler(" + obj.toString() + ");";
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
    @RequestMapping(value = "bookhandle/{userid}/{bookid}/{page}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookhandle(@PathVariable int userid,@PathVariable int bookid,@PathVariable int page) {
        String path = fileDao.findByFileID(bookid).getFileLocation();
        String res = readTxtFile(path+"\\txt\\",userid,bookid,page);
        String url = readTIF(path+"\\tif\\",page);
        int num = readNum(path+"\\txt\\");//Redis扩展
        BookPage bookPage = new BookPage(res,url,num);
        Object obj = JSONArray.toJSON(bookPage);
        String json = "BookHandleHandler("+obj.toString()+");";
        return json;
    }
}


