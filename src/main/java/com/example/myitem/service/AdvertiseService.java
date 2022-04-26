package com.example.myitem.service;

import com.example.myitem.entity.Advertise;
import com.example.myitem.mapper.AdvertiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertiseService {
    @Autowired
    public AdvertiseMapper advertiseMapper;

    public List<Advertise> getAdvertise() {
        return advertiseMapper.getAdvertise();
    }

    public int addAdvertise(String text) {
        return advertiseMapper.addAdvertise(text);
    }

    public void deAdvertiseById(Integer id){advertiseMapper.deAdvertiseById(id);}

    public List<Advertise> getAllIAdvertise() {
        return advertiseMapper.getAllIAdvertise();
    }
}
