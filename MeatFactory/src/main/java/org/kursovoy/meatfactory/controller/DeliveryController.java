package org.kursovoy.meatfactory.controller;

import org.kursovoy.meatfactory.model.Delivery;
import org.kursovoy.meatfactory.repository.DeliveryRepository;
import org.kursovoy.meatfactory.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryRepository deliveryRepository;
    public DeliveryController(DeliveryService deliveryService, DeliveryRepository deliveryRepository) {
        this.deliveryService = deliveryService;
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return ResponseEntity.ok(deliveryService.createDelivery(delivery));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery updatedDelivery) {
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    delivery.setOrder(updatedDelivery.getOrder());
                    delivery.setDeliveryDate(updatedDelivery.getDeliveryDate());
                    delivery.setStatus(updatedDelivery.getStatus());
                    return ResponseEntity.ok(deliveryRepository.save(delivery));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
