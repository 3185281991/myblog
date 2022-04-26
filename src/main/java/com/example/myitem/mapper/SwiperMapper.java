package com.example.myitem.mapper;

import com.example.myitem.entity.Swiper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SwiperMapper {
    void addSwiperImg(String imgPath);

    List<Swiper> randomImg();

    List<Swiper> getAllImg();

    void deleteImgById(Integer id);
}
