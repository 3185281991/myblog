package com.example.myitem.mapper;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileUploadMapper {
    /**
     * 处理浏览器文件上传请求
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile,String path);

    /**
     * 处理普通文件上传
     * @param file
     * @return
     */
    String upload(File file,String path);
}
