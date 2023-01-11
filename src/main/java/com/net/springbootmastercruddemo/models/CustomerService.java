package com.net.springbootmastercruddemo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerDao customerDao;

    /*get customer list*/
    public List<Customer> getAllCustomerList(){
        return customerDao.findAll();
    }

    /*save data customer*/
    public Customer saveCustomer(Customer customer){
        return this.customerDao.save(customer);
    }

    /*get a by id*/
    public Customer getCustomerById(Long id){
        Optional<Customer> optional = customerDao.findById(id);
        Customer customer = null;
        if (optional.isPresent()){
            customer = optional.get();
        }else{
            throw new RuntimeException(" Customer not found for id :: " + id);
        }
        return customer;
    }

    /* get a by id */
    public Customer findById(Long id){
        return customerDao.findById(id).orElse(null);
    }

    /* delete id */
    public void deleteCustomerById(Long id){
        this.customerDao.deleteById(id);
    }
}
