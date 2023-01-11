package com.net.springbootmastercruddemo.controllers;

import com.net.springbootmastercruddemo.models.CustomerService;
import com.net.springbootmastercruddemo.models.Payment;
import com.net.springbootmastercruddemo.models.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;


    //get all payment list
    @GetMapping(value = "/payments")
    public String showPaymentList(Model model){
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("customers", customerService.getAllCustomerList());

        return "views/payment/payment-list";
    }

    @RequestMapping(value = "/payments/findById")
    @ResponseBody
    public Optional<Payment> findById(Long id) {
        return paymentService.findById(id);
    }

    //Add Country
    @PostMapping(value = "/payments/addNew")
    public String addNew(Payment payment) {
        paymentService.save(payment);
        return "redirect:/payments";
    }

    @RequestMapping(value = "/payments/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Payment payment) {
        paymentService.save(payment);
        return "redirect:/payments";
    }

    @RequestMapping(value = "/payments/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id) {
        paymentService.delete(id);
        return "redirect:/payments";
    }
}
