package com.example.myitem.controller;

import com.example.myitem.entity.Impassion;
import com.example.myitem.entity.Root;
import com.example.myitem.service.RootService;
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
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:8081"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class RootController {
    @Autowired
    public RootService rootService;

    @PostMapping("/RootLogin")
    public ResponseEntity<List<Root>> RootLogin(Root root) {
        System.out.println(root);
        List<Root> result = rootService.RootLogin(root);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
