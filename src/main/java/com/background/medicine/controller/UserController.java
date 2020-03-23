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
import com.background.medicine.entity.mybooks;
import com.background.medicine.entity.mynote;
import com.background.medicine.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.background.medicine.controller.ReadBook.savetxtFile;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    RedisUtil redisUtil;

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
        if(redisUtil.get(userID+"Comment") != null){
            count = (int) redisUtil.get(userID+"Comment");
        }else {
            count = filecommentDao.countByUser(userID);
            redisUtil.set(userID+"Comment",count,600);
        }
        filecomment = filecommentDao.findByUser(userID,start,num);
        filecommentCount fc = new filecommentCount(filecomment,count);
        Object obj = JSONArray.toJSON(fc);
        String json = "CommentInfoHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "CommentAdd/{fileID}/{userID}/{title}/{content}/{fileName}/{userName}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String commenadd(@PathVariable int fileID,@PathVariable int userID,
                            @PathVariable String title,@PathVariable String content,
                            @PathVariable String fileName,@PathVariable String userName){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(new Date());
        filecomment filecomment = new filecomment(fileID,userID,title,content,now,fileName,userName);
        filecomment res = filecommentDao.save(filecomment);
        redisUtil.del(userID+"Comment");
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

    @RequestMapping(value = "CommentDelete/{commentID}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String commeninfo(@PathVariable int commentID,@PathVariable int userID){
        int res = 0;
        res = filecommentDao.deleteBycommentID(commentID);
        redisUtil.del(userID+"Comment");
        redisUtil.del(userID+"AllComment");
        Object obj = JSONArray.toJSON(res);
        String json = "CommentDeleteHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "addbook/{fileID}/{fileName}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String addbook(@PathVariable int fileID,@PathVariable String fileName,@PathVariable int userID){
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
            else{
                exists = 0;
                redisUtil.del(userID+"mybook");
            }
        }
        Object obj = JSONArray.toJSON(exists);
        String json = "addbookHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "bookDelete/{fileID}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String bookDelete(@PathVariable int fileID, @PathVariable int userID){
        int res = 0;
        res = mybooksDao.deleteByfileIDAnduserID(fileID,userID);
        redisUtil.del(userID+"mybook");
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
        if(redisUtil.get(userID+"mybook") != null)
            count = (int) redisUtil.get(userID+"mybook");
        else{
            count = mybooksDao.countByUser(userID);
            redisUtil.set(userID+"mybook",count,600);
        }

        bookshelf = mybooksDao.findByUserID(userID,start,num);
        bookshelfCount bc = new bookshelfCount(bookshelf,count);
        Object obj = JSONArray.toJSON(bc);
        String json = "mybooksHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "addnote/{fileID}/{userID}/{fileName}/{select}/{content}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String addnote(@PathVariable int fileID,@PathVariable int userID,@PathVariable String fileName,@PathVariable String select,@PathVariable String content){
        mynote mynote = new mynote(fileID,fileName,userID,select,content);
        mynote res = mynoteDao.save(mynote);
        redisUtil.del(userID+"mynote");
        Object obj = JSONArray.toJSON(res);
        String json = "addnoteHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "savenote",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public synchronized void savenote(@RequestBody noteInfo noteInfo){
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
        List<mynote> mynote = null;
        int count = 0;
        if(redisUtil.get(userID+"mynote") != null)
            count = (int) redisUtil.get(userID+"mynote");
        else{
            count = mynoteDao.countByUser(userID);
            redisUtil.set(userID+"mynote",count,600);
        }

        mynote = mynoteDao.findByUserID(userID,start,num);
        noteCount noteCount = new noteCount(mynote,count);
        Object obj = JSONArray.toJSON(noteCount);
        String json = "noteDetailHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "noteDelete/{noteID}/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String noteDelete(@PathVariable int noteID, @PathVariable int userID){
        int res = 0;
        res = mynoteDao.deleteBynoteID(noteID);
        redisUtil.del(userID+"mynote");
        Object obj = JSONArray.toJSON(res);
        String json = "noteDeleteHandler("+obj.toString()+");";
        return json;
    }
}
