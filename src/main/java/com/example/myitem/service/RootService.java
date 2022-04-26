package com.example.myitem.service;

import com.example.myitem.entity.Root;
import com.example.myitem.mapper.RootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootService {
    @Autowired
    public RootMapper rootMapper;

    public List<Root> RootLogin(Root root){return rootMapper.RootLogin(root);}
}
