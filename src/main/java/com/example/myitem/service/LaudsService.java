package com.example.myitem.service;

import com.example.myitem.entity.Lauds;
import com.example.myitem.mapper.LaudsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaudsService {
    @Autowired
    public LaudsMapper laudsMapper;

    public Integer isLaud(Lauds lauds) {
        return laudsMapper.isLaud(lauds);
    }

    public void insertLauds(Lauds lauds) {
        laudsMapper.insertLauds(lauds);
    }
}
