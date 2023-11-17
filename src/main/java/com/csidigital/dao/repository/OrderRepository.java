package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Employee;
import com.csidigital.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


   /* @Query(value ="SELECT o.id, o.ref FROM order_entity o INNER JOIN requirement r ON o.requirement_num = r.id LEFT JOIN project p ON o.id = p.id WHERE r.requirement_type = 'PROJECT' AND p.id IS NULL;", nativeQuery = true)
    List<Order> getProjectOrders();*/
   @Query(value ="SELECT o.* FROM order_entity o INNER JOIN requirement r ON o.requirement_num = r.id LEFT JOIN project p ON o.id = p.id WHERE r.requirement_type = 'PROJECT' AND p.id IS NULL;", nativeQuery = true)
   List<Order> getProjectOrders();


}
