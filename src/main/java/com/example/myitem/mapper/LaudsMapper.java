package com.example.myitem.mapper;

import com.example.myitem.entity.Lauds;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LaudsMapper {
    Integer isLaud(Lauds lauds);

    void insertLauds(Lauds lauds);
}
