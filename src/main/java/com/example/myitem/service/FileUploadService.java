package com.example.myitem.service;

import com.example.myitem.config.QCloudCosUtils;
import com.example.myitem.mapper.FileUploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileUploadService implements FileUploadMapper {

    @Autowired
    private QCloudCosUtils qCloudCosUtils;
    @Override
    public String upload(MultipartFile multipartFile,String path) {
        return qCloudCosUtils.upload(multipartFile,path);
    }

    @Override
    public String upload(File file,String path) {
        return qCloudCosUtils.upload(file,path);
    }
}


