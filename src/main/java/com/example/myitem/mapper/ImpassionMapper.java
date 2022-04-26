package com.example.myitem.mapper;

import com.example.myitem.entity.Impassion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImpassionMapper {
    public List<Impassion> getImpassion();

    public int addImpassion(String text);

    void deImpassionById(Integer id);

    public List<Impassion> getAllImpassion();

}
