package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.*;
import com.background.medicine.dto.filecommentCount;
import com.background.medicine.dto.onefilecommentCount;
import com.background.medicine.dto.userCount;
import com.background.medicine.entity.*;
import com.background.medicine.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class BackController {

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

    @Autowired
    UsersDao usersDao;

    @Autowired
    dayclickDao dayclickDao;

    @Autowired
    todayhotDao todayhotDao;

    @Autowired
    hotphraseDao hotphraseDao;

    @RequestMapping(value = "userManage/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String userManage(@PathVariable int start,@PathVariable int num) {
        List<Users> users = usersDao.findAll(start,num);
        int count = 0;
        if(redisUtil.get("AllUser") != null)
            count = (int) redisUtil.get("AllUser");
        else{
            count = usersDao.countAll();
            redisUtil.set("AllUser",count,1200);
        }

        userCount userCount = new userCount(users,count);
        Object obj = JSONArray.toJSON(userCount);
        String json = "userManageHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "userDelete/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String userManage(@PathVariable int userID) {
        int res = 0;
        res = usersDao.deleteByuserID(userID);
        redisUtil.del("AllUser");
        Object obj = JSONArray.toJSON(res);
        String json = "userDeleteHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "userModify/{userID}/{roleID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String userModify(@PathVariable int userID,@PathVariable int roleID) {

        int res = usersDao.modifyByuserID(roleID,userID);
        Object obj = JSONArray.toJSON(res);
        String json = "userModifyHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "CommentAll/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commeninfo(@PathVariable int start,
                             @PathVariable int num){
        List<filecomment> filecomment = null;
        filecomment = filecommentDao.findAll(start,num);
        int count = 0;
        if(redisUtil.get("AllComment") != null)
            count = (int) redisUtil.get("AllComment");
        else{
            count = filecommentDao.countAll();
            redisUtil.set("AllComment",count,1200);
        }

        filecommentCount fc = new filecommentCount(filecomment,count);
        Object obj = JSONArray.toJSON(fc);
        String json = "CommentAllHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "searchDelete/{content}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String userManage(@PathVariable String content) {
        content = "%"+content+"%";
        List<Integer> users = filecommentDao.finddelete(content);
        for(int i=0;i<users.size();i++)
            redisUtil.del(users.get(i)+"Comment");
        int res = filecommentDao.deleteBycontent(content);
        redisUtil.del("AllComment");
        Object obj = JSONArray.toJSON(res);
        String json = "searchDeleteHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "addUser/{username}/{email}/{password}/{roleID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String userManage(@PathVariable String username,@PathVariable String email,
                             @PathVariable String password,@PathVariable int roleID) {

        Users users = new Users();
        users.setUserName(username);
        users.setEmail(email);
        users.setPassword(password);
        users.setRoleID(roleID);
        Users users1 = usersDao.save(users);
        Object obj = JSONArray.toJSON(users1.userName.length());
        String json = "addUserHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "uploadFile/{remove}", method = RequestMethod.POST)
    public String uploadFile(@PathVariable int remove, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file1");//file是form-data中二进制字段对应的name
        Reader reader = null;
        String line;
        try {
            reader = new InputStreamReader(multipartFile.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(reader);
            if(remove == 1)
                br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] info = line.split("\t");
                if(usersDao.countByUser(info[0]) == 0) {
                    Users users = new Users(info[0], info[1], info[2], Integer.valueOf(info[3]));
                    usersDao.save(users);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @RequestMapping(value = "getDay",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getDay() {
        List<dayclick> dayclicks = dayclickDao.findday();
        Object obj = JSONArray.toJSON(dayclicks);
        String json = "getDayHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "getHot",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String gethot() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String day = sdf.format(date);
        List<todayhot> todayhot = todayhotDao.findday(day);
        Object obj = JSONArray.toJSON(todayhot);
        String json = "getHotHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "addHot/{fileID}/{fileName}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public synchronized String addHot(@PathVariable int fileID,@PathVariable String fileName) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String day = sdf.format(date);
        if(todayhotDao.countday(fileID,day) == 1)
            todayhotDao.updateday(fileID,day);
        else
            todayhotDao.save(new todayhot(fileID,fileName,1,day));
        Object obj = JSONArray.toJSON(1);
        String json = "addHotHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "getPhrase",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getPhrase() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String day = sdf.format(date);
        List<hotphrase> hotphrase = hotphraseDao.findday(day);
        Object obj = JSONArray.toJSON(hotphrase);
        String json = "getPhraseHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "getBook",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getBook() {
        List<Map<String,Integer>> res = fileDao.daybook();
        Object obj = JSONArray.toJSON(res);
        String json = "getBookHandler(" + obj.toString() + ");";
        return json;
    }
}
