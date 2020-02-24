package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.filecommentDao;
import com.background.medicine.dao.mynoteDao;
import com.background.medicine.dao.mybooksDao;
import com.background.medicine.dto.bookshelfCount;
import com.background.medicine.dto.filecommentCount;
import com.background.medicine.dto.noteCount;
import com.background.medicine.dto.noteInfo;
import com.background.medicine.entity.filecomment;
import com.background.medicine.entity.fileinfo;
import com.background.medicine.entity.mybooks;
import com.background.medicine.entity.mynote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.background.medicine.controller.ReadBook.savetxtFile;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    filecommentDao filecommentDao;

    @Autowired
    mybooksDao mybooksDao;

    @Autowired
    mynoteDao mynoteDao;


    @RequestMapping(value = "CommentInfo/{userID}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commeninfo(@PathVariable int userID,@PathVariable int start,
                            @PathVariable int num){
        List<filecomment> filecomment = null;
        int count = 0;
        filecomment = filecommentDao.findByUser(userID,start,num);
        count = filecommentDao.countByUser(userID);
        filecommentCount fc = new filecommentCount(filecomment,count);
        Object obj = JSONArray.toJSON(fc);
        String json = "CommentInfoHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "CommentAdd/{fileID}/{userID}/{title}/{content}/{fileName}/{userName}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commenadd(@PathVariable int fileID,@PathVariable int userID,
                            @PathVariable String title,@PathVariable String content,
                            @PathVariable String fileName,@PathVariable String userName){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(new Date());
        filecomment filecomment = new filecomment(fileID,userID,title,content,now,fileName,userName);
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

    @RequestMapping(value = "CommentDelete/{commentID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commeninfo(@PathVariable int commentID){
        int res = 0;
        res = filecommentDao.deleteBycommentID(commentID);
        Object obj = JSONArray.toJSON(res);
        String json = "CommentDeleteHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "addbook/{fileID}/{fileName}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addbook(@PathVariable int fileID,@PathVariable String fileName,@PathVariable int userID){
        int exists =mybooksDao.GetByFileIDAndUserID(fileID,userID);
        mybooks mybooks = new mybooks();
        mybooks val = null;
        if(exists == 0){
            mybooks.setFileID(fileID);
            mybooks.setFileName(fileName);
            mybooks.setUserID(userID);
            val=mybooksDao.save(mybooks);
            if(val.fileID == -1)
                exists = -1;
            else
                exists = 0;
        }
        Object obj = JSONArray.toJSON(exists);
        String json = "addbookHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "bookDelete/{fileID}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookDelete(@PathVariable int fileID, @PathVariable int userID){
        int res = 0;
        res = mybooksDao.deleteByfileIDAnduserID(fileID,userID);
        Object obj = JSONArray.toJSON(res);
        String json = "bookDeleteHandler("+obj.toString()+");";
        return json;
    }


    @RequestMapping(value = "mybooks/{userID}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String bookshelf(@PathVariable int userID,@PathVariable int start,
                             @PathVariable int num){
        List<mybooks> bookshelf = null;
        int count = 0;
        bookshelf = mybooksDao.findByUserID(userID,start,num);
        count = mybooksDao.countByUser(userID);
        bookshelfCount bc = new bookshelfCount(bookshelf,count);
        Object obj = JSONArray.toJSON(bc);
        String json = "mybooksHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "addnote/{fileID}/{userID}/{fileName}/{select}/{content}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addnote(@PathVariable int fileID,@PathVariable int userID,@PathVariable String fileName,@PathVariable String select,@PathVariable String content){
        mynote mynote = new mynote(fileID,fileName,userID,select,content);
        mynote res = mynoteDao.save(mynote);

        Object obj = JSONArray.toJSON(res);
        String json = "addnoteHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "savenote",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void savenote(@RequestBody noteInfo noteInfo){
        savetxtFile(noteInfo.info,noteInfo.users,noteInfo.files,noteInfo.pages);
    }

    @RequestMapping(value = "getnote/{noteID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addnote(@PathVariable int noteID){
        mynote mynote = mynoteDao.findByNoteID(noteID);
        Object obj = JSONArray.toJSON(mynote);
        String json = "getnoteHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "usernote/{userID}/{fileID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String usernote(@PathVariable int userID,@PathVariable int fileID){
        List<mynote> mynote = mynoteDao.findByUserIDAndFileID(userID,fileID);
        Object obj = JSONArray.toJSON(mynote);
        String json = "usernoteHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "noteDetail/{userID}/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String noteDetail(@PathVariable int userID,@PathVariable int start,
                          @PathVariable int num){
        List<mynote> mynote = mynoteDao.findByUserID(userID,start,num);
        int count = mynoteDao.countByUser(userID);
        noteCount noteCount = new noteCount(mynote,count);
        Object obj = JSONArray.toJSON(noteCount);
        String json = "noteDetailHandler("+obj.toString()+");";
        return json;
    }
}
