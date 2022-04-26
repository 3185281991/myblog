package com.example.myitem.service;

import com.example.myitem.entity.Swiper;
import com.example.myitem.mapper.SwiperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperService {
    @Autowired
    public SwiperMapper swiperMapper;

    public void addSwiperImg(String imgPath){ swiperMapper.addSwiperImg(imgPath);}

    public List<Swiper> randomImg(){return swiperMapper.randomImg();}

    public List<Swiper> getAllImg(){return swiperMapper.getAllImg();}

    public void deleteImgById(Integer id){swiperMapper.deleteImgById(id);}
}
