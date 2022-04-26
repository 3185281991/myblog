package com.example.myitem.controller;

import com.example.myitem.entity.Blog;
import com.example.myitem.service.BlogService;
import com.example.myitem.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//允许所有跨域
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:8081"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class BlogController {
    @Autowired
    public BlogService blogService;
    @Autowired
    public FileUploadService fileUploadService;

    @GetMapping("/getAllBlog")
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> result = blogService.getAllBlog();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addBlog")
    public ResponseEntity<Integer> addBlog(String title, String author, String label, String type, String content, String time, String top, MultipartFile[] file) throws IOException {
        //多张图片的话循环遍历，依次创建以及最终拼接成所有字符串的绝对路径
        Blog blog = new Blog();
        if (file != null) {
//            根据文件数量来调用重载的上传方法
            String rs = "";
            if (file.length == 1) {
                rs =rs+ fileUploadService.upload(file[0], "allImg/");
            } else {
                System.out.println(file.length);
                for (int i = 0; i < file.length; i++) {
                    //这里的路径必须是springbooot项目运行端口
                    String path = fileUploadService.upload(file[i], "allImg/");
                    System.out.println(path);
                    if (rs.equals("")) {
                        rs = rs + path;
                    } else {
                        rs = rs + "," + path;
                    }
                }

            }
            blog.setImg(rs);
        } else {
            blog.setImg("");
        }
        // 将拼接的字符串放入数据库
        blog.setTitle(title);
        blog.setAuthor(author);
        blog.setLabel(label);
        blog.setType(type);
        blog.setContent(content);
        blog.setTime(time);
        blog.setTop(top);
        Integer data = blogService.addBlog(blog);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getBlogByType")
    public ResponseEntity<List<Blog>> getBlogByType(String type) {
        List<Blog> result = blogService.getBlogByType(type);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getLabel")
    public ResponseEntity<List<String>> getLabel() {
        List<String> result = blogService.getLabel();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/Recommend")
    public ResponseEntity<List<Blog>> Recommend() {
        List<Blog> result = blogService.Recommend();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/likeFind")
    public ResponseEntity<List<Blog>> likeFind(String str) {
        List<Blog> result = blogService.likeFind(str);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/SearchInfo")
    public ResponseEntity<List<Blog>> SearchInfo() {
        List<Blog> result = blogService.SearchInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //根据bid获得文章信息，留言板的是1
    @GetMapping("/getById")
    public ResponseEntity<List<Blog>> getById(Integer bid) {
        List<Blog> result = blogService.getById(bid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addLaud")
    public ResponseEntity<String> addLaud(Integer bid) {
        blogService.addLaud(bid);
        return new ResponseEntity<>("谢谢你的点赞 。", HttpStatus.OK);
    }

    @PostMapping("/addView")
    public ResponseEntity<String> addView(Integer bid) {
        blogService.addView(bid);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/addCommentNum")
    public ResponseEntity<String> addCommentNum(Integer bid) {
        blogService.addComment(bid);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    //    后台部分

    //返回每种类型的数量
    @PostMapping("/groupByType")
    public ResponseEntity<List<Map>> groupByType() {
        List<Map> result = blogService.groupByType();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //取8个最新的博客
    @GetMapping("/getNew")
    public ResponseEntity<List<Blog>> getNew() {
        List<Blog> result = blogService.getNew();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //返回总数
    @PostMapping("/getAllSum")
    public ResponseEntity<List<Map>> getAllSum() {
        List<Map> result = blogService.getAllSum();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //评论，浏览，点赞排行
    @GetMapping("/laudsPx")
    public ResponseEntity<List<Blog>> laudsPx() {
        List<Blog> result = blogService.laudsPx();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/viewsPx")
    public ResponseEntity<List<Blog>> viewsPx() {
        List<Blog> result = blogService.viewsPx();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/commentsPx")
    public ResponseEntity<List<Blog>> commentsPx() {
        List<Blog> result = blogService.commentsPx();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //作则发篇数量
    @PostMapping("/authorText")
    public ResponseEntity<List<Map>> authorText() {
        List<Map> result = blogService.authorText();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //删除博客
    @PostMapping("/deleteById")
    public ResponseEntity<String> deleteById(Integer bid) {
        System.out.println(bid + "已删除。");
        blogService.deleteById(bid);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }

    //拿到顺序博客
    @GetMapping("/getBlogsSequence")
    public ResponseEntity<List<Blog>> getBlogsSequence() {
        List<Blog> result = blogService.getBlogsSequence();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //更改留言板信息
    @PostMapping("/updateMessageBoard")
    public ResponseEntity<String> updateMessageBoard(String content) {
        blogService.updateMesaageBorad(content);
        return new ResponseEntity<>("已修改留言板内容。", HttpStatus.OK);
    }


}
