package org.kursovoy.meatfactory.controller;

import org.kursovoy.meatfactory.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    public ThymeleafController(CustomerRepository customerRepository,
                               EmployeeRepository employeeRepository,
                               ProductRepository productRepository,
                               SupplierRepository supplierRepository, OrderRepository orderRepository, DeliveryRepository deliveryRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/customers-view")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @GetMapping("/employees-view")
    public String showEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees";
    }

    @GetMapping("/products-view")
    public String showProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/suppliers-view")
    public String showSuppliers(Model model) {
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "suppliers";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/orders-view")
    public String getOrderPage(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @GetMapping("/deliveries-view")
    public String getDeliveryPage(Model model) {
        model.addAttribute("deliveries", deliveryRepository.findAll());
        return "deliveries";
    }

    @GetMapping("/query")
    public String getQueryPage() {
        return "query";
    }

}
