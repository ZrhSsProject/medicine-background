package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.*;
import com.background.medicine.dto.filecommentCount;
import com.background.medicine.dto.onefilecommentCount;
import com.background.medicine.dto.userCount;
import com.background.medicine.entity.Users;
import com.background.medicine.entity.filecomment;
import com.background.medicine.entity.fileinfo;
import com.background.medicine.entity.mynote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class BackController {

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

    @RequestMapping(value = "userManage/{start}/{num}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String userManage(@PathVariable int start,@PathVariable int num) {
        List<Users> users = usersDao.findAll(start,num);
        int count = usersDao.countAll();
        userCount userCount = new userCount(users,count);
        Object obj = JSONArray.toJSON(userCount);
        String json = "userManageHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "userDelete/{userID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String userManage(@PathVariable int userID) {
        int res = usersDao.deleteByuserID(userID);
        Object obj = JSONArray.toJSON(res);
        String json = "userDeleteHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "userModify/{userID}/{roleID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String userModify(@PathVariable int userID,@PathVariable int roleID) {
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
        int count = 0;
        filecomment = filecommentDao.findAll(start,num);
        count = filecommentDao.countAll();
        filecommentCount fc = new filecommentCount(filecomment,count);
        Object obj = JSONArray.toJSON(fc);
        String json = "CommentAllHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "searchDelete/{content}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String userManage(@PathVariable String content) {
        content = "%"+content+"%";
        int res = filecommentDao.deleteBycontent(content);
        Object obj = JSONArray.toJSON(res);
        String json = "searchDeleteHandler(" + obj.toString() + ");";
        return json;
    }

    @RequestMapping(value = "addUser/{username}/{email}/{password}/{roleID}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String userManage(@PathVariable String username,@PathVariable String email,
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
}
