package com.example.myitem.controller;

import com.example.myitem.entity.Impassion;
import com.example.myitem.service.ImpassionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//允许所有跨域
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:81"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class ImpassionController {
    @Autowired
    public ImpassionService impassionService;

    @GetMapping("/getImpassion")
    public ResponseEntity<List<Impassion>> getImpassion() {
        List<Impassion> result = impassionService.getImpassion();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/protect/addImpassion")
    public ResponseEntity<Integer> addImpassion(String text) {
       int result = impassionService.addImpassion(text);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/protect/deImpassionById")
    public ResponseEntity<String> deImpassionById(Integer id) {
        impassionService.deImpassionById(id);
        return new ResponseEntity<>("一条每日一句记录已删除", HttpStatus.OK);
    }

    @GetMapping("/getAllImpassion")
    public ResponseEntity<List<Impassion>> getAllImpassion() {
        List<Impassion> result = impassionService.getAllImpassion();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
