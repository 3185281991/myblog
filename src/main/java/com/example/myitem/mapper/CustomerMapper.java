package com.example.myitem.mapper;

import com.example.myitem.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<Customer> getCommentByBid(Integer bid);

    void addComment(Customer customer);
}
