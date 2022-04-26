package com.example.myitem.service;

import com.example.myitem.entity.Impassion;
import com.example.myitem.mapper.ImpassionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpassionService {
    @Autowired
    public ImpassionMapper impassionMapper;

    public List<Impassion> getImpassion() {
        return impassionMapper.getImpassion();
    }

    public int addImpassion(String text) {
        return impassionMapper.addImpassion(text);
    }

    public void deImpassionById(Integer id){impassionMapper.deImpassionById(id);}

    public List<Impassion> getAllImpassion() {
        return impassionMapper.getAllImpassion();
    }
}
