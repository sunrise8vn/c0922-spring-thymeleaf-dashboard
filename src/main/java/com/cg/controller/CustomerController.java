package com.cg.controller;


import com.cg.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final Map<Integer, Customer> customers;
    private static Integer id = 0;

    static {
        customers = new HashMap<>();

        Customer c1 = new Customer(1L, "NVA", "nva@co.cc", "2345", BigDecimal.valueOf(10000), "28 Nguyễn Tri Phương");
        Customer c2 = new Customer(2L, "NVB", "nvb@co.cc", "3456", BigDecimal.valueOf(20000), "29 Lê Lợi");
        Customer c3 = new Customer(3L, "NVC", "nvc@co.cc", "4567", BigDecimal.valueOf(30000), "30 Hùng Vương");
        Customer c4 = new Customer(4L, "NVD", "nvd@co.cc", "5678", BigDecimal.valueOf(40000), "32 Nguyễn Huệ");

        id++;
        customers.put(id,c1);
        id++;
        customers.put(id,c2);
        id++;
        customers.put(id,c3);
        id++;
        customers.put(id,c4);

    }

    @GetMapping
    public String showListPage(Model model) {
        model.addAttribute("customers", customers.values());

        return "customer/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute Customer customer, Model model) {
        id++;
        customer.setId(Long.valueOf(id.toString()));
        customer.setBalance(BigDecimal.ZERO);

        customers.put(id, customer);

        model.addAttribute("customer", new Customer());

        return "customer/create";
    }
}
