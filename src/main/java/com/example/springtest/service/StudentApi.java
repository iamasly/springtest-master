package com.example.springtest.service;

import com.example.springtest.dao.IStudentDao;
import com.example.springtest.entity.Student;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
//表明 restservice
@Api(tags="学生管理相关接口")
@RequestMapping(value = "/student")
//把URL映射过来 管理的是student地址下面所有的方法
public class   StudentApi {
    @Autowired
    //注入dao
    private IStudentDao dao;
    @GetMapping(value="/login/{name}/{password}")
    //结合RequestMapping和Get方法请求,login地址下面只能使用get方法
    public Student login(@PathVariable String name,@PathVariable String password){
        //path 是路径参数
        Student student=dao.findStudentByNameAndPassword(name,password);
        if(student!=null){
            return student;
        }else {
            return null;
        }
        //存在json和java对象的转换
        //get请求可见可侦测
        //传参有问题
        //涉及登录的用post传参
        //get  公开的
        //springboot
    }

    //------------------------------------------------------------------------------------\
    @PostMapping(value="/login")
    //结合RequestMapping和Get方法请求,login地址下面只能使用get方法
    @ApiOperation("学生登入")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户名",defaultValue = "admin",required = true),
            @ApiImplicitParam(name="password",value="密码",defaultValue = "123456",required = true)

    })
    @ApiResponses({
        @ApiResponse(code=200,message="操作成功",response=Student.class)
    })
    public Student login1(@RequestParam String name,@RequestParam String password) {
        //path 是路径参数
        Student student = dao.findStudentByNameAndPassword(name, password);
            return student;
            //在body里面
    }
    //------------------------------------------------------------------------------------\
    @ApiOperation("学生注册")
    @ApiImplicitParam(name="student",value="学生信息JSON数据",required = true)
    @PostMapping(value="/register")
    public Boolean registerStudent(@RequestBody Student student) {
        //path 是路径参数

         dao.save(student);
            return true;
            //josn对象直接扔进了
        //在body里面
        //前后端如何对接参数，josn对象，路径代表啥
        //swagger
    }
    //post dele put get 增删改查
//    -----------------------------------------------------------------------------
    @PutMapping(value = "/update")
    public Boolean updateStudent(@RequestBody Student student ){
        dao.save(student);
        return true;
    }
    //-----------------------------------------------------------------------------
    @DeleteMapping(value = "/delete")
    public Boolean DeleteStudent(@RequestParam Long id ){
        dao.deleteById(id);
        return true;
    }
    //路径的权限 验证token的步骤
    //token jwt体系 费时间
    //通过微信，qq登录
    //token产生，认证服务器
}