package com.example.myitem.service;

import com.example.myitem.entity.Customer;
import com.example.myitem.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    public CustomerMapper customerMapper;

    public List<Customer> getCommentByBid(Integer bid) {
        return customerMapper.getCommentByBid(bid);
    }

    public void addComment(Customer customer) {
        customerMapper.addComment(customer);
    }
}
