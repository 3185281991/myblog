package com.example.myitem.controller;

import com.example.myitem.entity.Customer;
import com.example.myitem.entity.Review;
import com.example.myitem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:8081"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class ReviewController {
    @Autowired
    public ReviewService reviewService;

    @PostMapping("/getReviewsByEmail")
    public ResponseEntity<List<Review>> getReviewsByEmail(Customer customer) {
        List<Review> result = reviewService.getReviewsByEmail(customer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(Review review) {
        reviewService.addReview(review);
        return new ResponseEntity<>("添加回复成功 。", HttpStatus.OK);
    }
}
