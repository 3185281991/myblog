package com.example.myitem.controller;

import com.example.myitem.config.JwtUtils;
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
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:81"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class RootController {
    @Autowired
    public RootService rootService;
    @GetMapping("/protect/AutoLogin")
    public ResponseEntity<String> AutoLogin(Root root) {
        return new ResponseEntity<>("识别成功", HttpStatus.OK);
    }

    @PostMapping("/protect/RootLogin")
    public ResponseEntity<List> RootLogin(Root root) {
        List result = rootService.RootLogin(root);
        if(result.size()!=0){
            result.add(JwtUtils.getToken(root));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
