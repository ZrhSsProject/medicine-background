package com.background.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.background.medicine.dao.UsersDao;
import com.background.medicine.dao.dayclickDao;
import com.background.medicine.entity.Users;
import com.background.medicine.entity.dayclick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("")
public class IndexController {
    @Autowired
    UsersDao usersDao;

    @Autowired
    dayclickDao dayclickDao;
//这个是自动导入 用户dao    包的自动填充，针对DI 区别AOP

    @RequestMapping(value = "test",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Users test(){
        Users users = new Users();
        users.setUserID(1);
        users.setUserName("1");
        users.setPassword("1");
        users.setRoleID(1);
        users.setDepartment("1");
        return users;
    }

    @RequestMapping(value = "find/{userName}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Users find(@PathVariable String userName){//获取用户传入url的信息，配置要求，url路径变量
        Users users = usersDao.findByUserName(userName);
        return users;
    }

    @RequestMapping(value = "login/{userName}/{password}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String login(@PathVariable String userName,@PathVariable String password){
        Users users = null;
        users = usersDao.findByUserNameAndPassword(userName,password);
        if(users == null){
            users = new Users();
            users.setUserName("用户名不存在");
        }else{
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String day = sdf.format(date);
            if(dayclickDao.countday(day) == 1)
                dayclickDao.updateday(day);
            else
                dayclickDao.save(new dayclick(1,day));
        }
        Object obj = JSONArray.toJSON(users);
        String json = "LoginHandler("+obj.toString()+");";
        return json;
    }

    @RequestMapping(value = "register/{userName}/{email}/{password}",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String register(@PathVariable String userName,@PathVariable String email,@PathVariable String password){
        Users users = new Users();
        users.setUserName(userName);
        users.setEmail(email);
        users.setPassword(password);
        //外键约束
        users.setRoleID(1);
        users.setDepartment("1");
        Users val = usersDao.save(users);
        Object obj = JSONArray.toJSON(users);
        String json = "RegisterHandler("+obj.toString()+");";
        return json;

    }
}
//这个是目前的框架
//刚才的错误是我配置了springdatajpa，需要配置数据库信息。
//后面得换Mybatis。语句得自定制

//{"userID":1,"userName":"1","password":"1","roleID":0,"department":"1"}
//json数据格式，可以用于数据和外界交互
//ResponseBody 这个是配置的关键，开启这个，默认返回json格式

//数据库查询做好了
//你把其他表也创建下吧，在entity中，，好，然后需要dao映射吗不用今天就结束了


