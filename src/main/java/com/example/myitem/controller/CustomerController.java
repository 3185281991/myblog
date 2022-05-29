package com.example.myitem.controller;

import com.example.myitem.entity.Customer;
import com.example.myitem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//允许所有跨域
@CrossOrigin(origins = {"http://124.221.69.31", "http://124.221.69.31:81"}, maxAge = 3600)
@RequestMapping("/myBlog")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCommentByBid")
    public ResponseEntity<List<Customer>> getCommentByBid(Integer bid) {
        List<Customer> result = customerService.getCommentByBid(bid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(Customer customer) {
        customerService.addComment(customer);
        return new ResponseEntity<String>("发表评论成功 。", HttpStatus.OK);
    }
}
