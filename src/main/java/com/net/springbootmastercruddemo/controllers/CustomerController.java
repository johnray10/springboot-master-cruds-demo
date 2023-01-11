package com.net.springbootmastercruddemo.controllers;

import com.net.springbootmastercruddemo.config.FileUploadUtil;
import com.net.springbootmastercruddemo.models.Customer;
import com.net.springbootmastercruddemo.models.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // RESTful API methods for Retrieval operations
    @GetMapping(value = "/customers")
    public String showCustomer(Model model) {
        List<Customer> customers  = customerService.getAllCustomerList();
        model.addAttribute("customers", customers);
        return "views/customer/customer-list";
    }

    //show customer-add
    @GetMapping(value = "/addCustomer")
    public String addCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customers", customer);
        return "views/customer/customer-add";
    }

    // RESTful API method for Create operation
    @PostMapping("/create-customer")
    public RedirectView saveEmployee(@ModelAttribute("customers") Customer customer,
                                     @RequestParam("image") MultipartFile multipartFile) throws IOException {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            customer.setPhoto(fileName);
            Customer saveCustomer = customerService.saveCustomer(customer);
            String uploadDir = "user-photos/" + saveCustomer.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return new RedirectView("/customers", true);

    }

    // RESTful API method for get by id Update operation
    @GetMapping(value = "/edit-customer/{id}")
    public String UpdateImage(@PathVariable(value = "id") Long id, Model model) {
        List<Customer> customerList = customerService.getAllCustomerList();
        model.addAttribute("customers", customerList);
        // set employee as a model attribute to pre-populate the form
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customers", customer);
        return "views/customer/customer-update";
    }

    // RESTful API method for Delete operation
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Long id) {
        // call delete employee method
        this.customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }
}
