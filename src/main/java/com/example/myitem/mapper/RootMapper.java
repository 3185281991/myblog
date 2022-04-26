package com.example.myitem.mapper;

import com.example.myitem.entity.Root;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RootMapper {
    public List<Root> RootLogin(Root root);
}
