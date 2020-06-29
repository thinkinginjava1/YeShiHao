package com.yeshihao.sqlserverspringboot1.controller;

import com.yeshihao.sqlserverspringboot1.entity.Student;
import com.yeshihao.sqlserverspringboot1.respostroy.StudentRespostroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private StudentRespostroy rs;
    @GetMapping("/hello")
    public String sayHello(){
        return "你好";
    }
    @GetMapping("/adduser")
    public String addStudent(){
        Student s1=new Student();
        s1.setName("yeshihao");
        s1.setAge(1);
        s1.setId(1);
        rs.save(s1);
        return "ok";
    }
}
