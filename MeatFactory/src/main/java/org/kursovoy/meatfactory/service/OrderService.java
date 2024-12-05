package org.kursovoy.meatfactory.service;

import org.kursovoy.meatfactory.model.Order;
import org.kursovoy.meatfactory.repository.CustomerRepository;
import org.kursovoy.meatfactory.repository.EmployeeRepository;
import org.kursovoy.meatfactory.repository.OrderRepository;
import org.kursovoy.meatfactory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        EmployeeRepository employeeRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        validateOrder(order);
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(existingOrder -> {
            validateOrder(updatedOrder);

            existingOrder.setCustomer(updatedOrder.getCustomer());
            existingOrder.setEmployees(updatedOrder.getEmployees());
            existingOrder.setProducts(updatedOrder.getProducts());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());

            return orderRepository.save(existingOrder);
        });
    }


    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateOrder(Order order) {
        if (order.getCustomer() == null || !customerRepository.existsById(order.getCustomer().getId())) {
            throw new IllegalArgumentException("Invalid or missing customer ID.");
        }

        List<Long> employeeIds = order.getEmployees().stream().map(e -> e.getId()).toList();
        if (employeeIds.isEmpty() || employeeRepository.findAllById(employeeIds).size() != employeeIds.size()) {
            throw new IllegalArgumentException("Invalid or missing employee IDs.");
        }

        List<Long> productIds = order.getProducts().stream().map(p -> p.getId()).toList();
        if (productIds.isEmpty() || productRepository.findAllById(productIds).size() != productIds.size()) {
            throw new IllegalArgumentException("Invalid or missing product IDs.");
        }
    }

}
