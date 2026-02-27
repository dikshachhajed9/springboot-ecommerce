package com.ecommerce.sb_ecom.repositories;


import com.ecommerce.sb_ecom.Model.Order;
import com.ecommerce.sb_ecom.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
