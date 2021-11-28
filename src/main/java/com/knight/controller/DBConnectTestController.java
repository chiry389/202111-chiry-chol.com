package com.knight.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DBConnectTestController {
//    @Autowired
//    private SqlSession sqlSession;

//    @GetMapping("/hello")
//    public List<TestDTO> getHelloWorld() {
//        return sqlSession.getMapper(TestDAO.class).getTestData();
//    }
}
