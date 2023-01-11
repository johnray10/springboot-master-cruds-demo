package com.net.springbootmastercruddemo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired private PaymentDao paymentDao;

    //Get All Payment
    public List<Payment> findAll(){
        return paymentDao.findAll();
    }

    //Get Payment By Id
    public Optional<Payment> findById(Long id) {
        return paymentDao.findById(id);
    }

    //Delete
    public void delete(Long id) {
        paymentDao.deleteById(id);
    }

    //Update
    public void save(Payment payment) {
        paymentDao.save(payment);
    }
}
