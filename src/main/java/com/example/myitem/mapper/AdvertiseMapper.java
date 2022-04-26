package com.example.myitem.mapper;

import com.example.myitem.entity.Advertise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertiseMapper {

    public List<Advertise> getAdvertise();

    public int addAdvertise(String info);

    void deAdvertiseById(Integer id);

    public List<Advertise> getAllIAdvertise();
}
