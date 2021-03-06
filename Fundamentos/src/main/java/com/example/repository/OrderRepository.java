package com.example.repository;

import com.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findOrderById(Long id);

    @Query("SELECT o FROM Order o WHERE o.restaurant.id=?1 and o.status.id=?2")
    List<Order> findOrdersByRestaurantAndStatus(Long restaurantId, Long statusId);

    @Query("SELECT o FROM Order o WHERE o.restaurant.id=?1")
    List<Order> findOrdersByRestaurant(Long id);
}
