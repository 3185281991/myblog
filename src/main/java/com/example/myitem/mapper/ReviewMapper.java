package com.example.myitem.mapper;

import com.example.myitem.entity.Customer;
import com.example.myitem.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> getReviewsByEmail(Customer customer);

    void addReview(Review Review);

}
