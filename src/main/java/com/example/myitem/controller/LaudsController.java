package com.example.myitem.controller;

import com.example.myitem.entity.Lauds;
import com.example.myitem.service.LaudsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//允许所有跨域
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:81"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class LaudsController {

    @Autowired
    public LaudsService laudsService;

    @PostMapping("/isLaud")
    public ResponseEntity<Integer> isLaud(Lauds lauds) {
        Integer isLaud = laudsService.isLaud(lauds);
        //当存在对应的博客id和用户信息email时，返回对应的isLaud状态，若不存在，就添加该bid和用户信息email
        return new ResponseEntity<>(isLaud, HttpStatus.OK);
    }

    @PostMapping("/insertLauds")
    public ResponseEntity<String> insertLauds(Lauds ladus) {
        laudsService.insertLauds(ladus);
        return new ResponseEntity<>("已添加对应的用户博客信息点赞。", HttpStatus.OK);
    }
}
