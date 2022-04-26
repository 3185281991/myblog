package com.example.myitem.mapper;

import com.example.myitem.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper {
    List<Blog> getAllBlog();

    List<Blog> getById(Integer bid);

    void addLaud(Integer bid);

    void addView(Integer bid);

    void addComment(Integer bid);

    List<Blog> getBlogByType(String type);

    Integer addBlog(Blog blog);

    List<Blog> likeFind(String str);

    List<Blog> SearchInfo();

    List<Blog> Recommend();

    List<String> getLabel();

    List<Map> groupByType();

    List<Blog> getNew();

    List<Map> getAllSum();

    List<Blog> laudsPx();

    List<Blog> viewsPx();

    List<Blog> commentsPx();

    List<Map> authorText();

    void deleteById(Integer bid);

    List<Blog> getBlogsSequence();

    void updateMessageBoard(String content);

}
