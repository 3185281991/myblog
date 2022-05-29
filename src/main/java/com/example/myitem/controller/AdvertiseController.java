package com.example.myitem.controller;

import com.example.myitem.entity.Advertise;
import com.example.myitem.entity.Impassion;
import com.example.myitem.service.AdvertiseService;
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
public class AdvertiseController {
    @Autowired
    public AdvertiseService advertiseService;

    @GetMapping("/getAdvertise")
    public ResponseEntity<List<Advertise>> getAdvertise() {
        List<Advertise> result = advertiseService.getAdvertise();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/protect/addAdvertise")
    public ResponseEntity<Integer> addAdvertise(String text) {
        int result = advertiseService.addAdvertise(text);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/protect/deAdvertiseById")
    public ResponseEntity<String> deAdvertiseById(Integer id) {
         advertiseService.deAdvertiseById(id);
        return new ResponseEntity<>("一条介绍记录已删除", HttpStatus.OK);
    }

    @GetMapping("/getAllIAdvertise")
    public ResponseEntity<List<Advertise>> getAllIAdvertise() {
        List<Advertise> result = advertiseService.getAllIAdvertise();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
