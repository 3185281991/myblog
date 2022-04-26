package com.example.myitem.service;

import com.example.myitem.entity.Customer;
import com.example.myitem.entity.Review;
import com.example.myitem.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    public ReviewMapper reviewMapper;

    public List<Review> getReviewsByEmail(Customer customer) {
        return reviewMapper.getReviewsByEmail(customer);
    }

    public void addReview(Review review) {
        reviewMapper.addReview(review);
    }
}
