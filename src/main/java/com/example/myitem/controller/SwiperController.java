package com.example.myitem.controller;

import com.example.myitem.entity.Swiper;
import com.example.myitem.service.FileUploadService;
import com.example.myitem.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:81"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class SwiperController {
    @Autowired
    public SwiperService swiperService;
    @Autowired
    public FileUploadService fileUploadService;

    //添加首页轮播图
    @PostMapping("/protect/addSwiperImg")
    public ResponseEntity<String> addSwiperImg(MultipartFile file) throws IOException {
        String path = null;
        if (file != null) {
            path = fileUploadService.upload(file, "swiper/");
        }

        swiperService.addSwiperImg(path);
        //这里的路径必须是springbooot项目运行端口
        return new ResponseEntity<>("您添加了一张轮播图片。", HttpStatus.OK);
    }

    //随机五张轮播图
    @PostMapping("/randomImg")
    public ResponseEntity<List<Swiper>> randomImg() {
        List<Swiper> result = swiperService.randomImg();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //所有轮播图片
    @PostMapping("/protect/getAllImg")
    public ResponseEntity<List<Swiper>> getAllImg() {
        List<Swiper> result = swiperService.getAllImg();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //删除图片
    @PostMapping("/protect/deleteImgById")
    public ResponseEntity<String> deleteImgById(Integer id) {
        swiperService.deleteImgById(id);
        return new ResponseEntity<>("您删除了一轮播张图片。", HttpStatus.OK);
    }
}
