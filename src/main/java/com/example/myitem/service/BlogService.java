package com.example.myitem.service;

import com.example.myitem.entity.Blog;
import com.example.myitem.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    public BlogMapper blogMapper;

    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    public List<Blog> getById(Integer bid) {
        return blogMapper.getById(bid);
    }

    public List<Blog> getBlogByType(String str) {
        return blogMapper.getBlogByType(str);
    }

    public Integer addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    public void addLaud(Integer bid) {
        blogMapper.addLaud(bid);
    }

    public void addView(Integer bid) {
        blogMapper.addView(bid);
    }

    public void addComment(Integer bid) {
        blogMapper.addComment(bid);
    }

    public List<Blog> likeFind(String str) {
        return blogMapper.likeFind(str);
    }

    public List<Blog> SearchInfo() {
        return blogMapper.SearchInfo();
    }

    public List<String> getLabel() {
        return blogMapper.getLabel();
    }

    public List<Blog> Recommend() {
        return blogMapper.Recommend();
    }

   public  List<Map> groupByType(){
        return blogMapper.groupByType();
   };

    public  List<Blog> getNew(){
        return blogMapper.getNew();
    };

    public  List<Map> getAllSum(){
        return blogMapper.getAllSum();
    };

    public List<Blog> laudsPx(){return blogMapper.laudsPx();}

    public List<Blog> viewsPx(){return blogMapper.viewsPx();}

    public List<Blog> commentsPx(){return blogMapper.commentsPx();}

    public List<Map> authorText(){return blogMapper.authorText();}

    public void deleteById(Integer bid){ blogMapper.deleteById(bid);}

    public List<Blog> getBlogsSequence(){return blogMapper.getBlogsSequence();}

    public void updateMesaageBorad(String content){blogMapper.updateMessageBoard(content);}

}
