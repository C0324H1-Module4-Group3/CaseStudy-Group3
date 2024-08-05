package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.dto.OrderDetailDTO;
import com.example.casestudymodule4.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrderRepository extends JpaRepository<Order, Integer> {

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    o.id AS idOrder,\n" +
            "    u.name AS customerName,\n" +
            "    p.name AS productName,\n" +
            "    c.name AS categoryName,\n" +
            "    SUM(od.quantity * sp.price) AS totalMoney\n" +
            "FROM\n" +
            "    users u\n" +
            "        JOIN\n" +
            "    orders o ON u.id = o.customer_id\n" +
            "        JOIN\n" +
            "    order_details od ON o.id = od.order_id\n" +
            "        JOIN\n" +
            "    sku_products sp ON od.sku_id = sp.id\n" +
            "        JOIN\n" +
            "    products p ON sp.product_id = p.id\n" +
            "        JOIN\n" +
            "    categories c ON p.category_id = c.id\n" +
            "WHERE\n" +
            "    sp.status = 'Còn'\n" +
            "  AND o.booking_date BETWEEN '2024-20-08' AND '2024-10-08'\n" +
            "GROUP BY\n" +
            "    o.id, u.name, p.name, c.name;")

    Iterable<OrderDetailDTO> getOrderDetailDTO();

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    o.id AS idOrder,\n" +
            "    u.name AS customerName,\n" +
            "    p.name AS productName,\n" +
            "    c.name AS categoryName,\n" +
            "    SUM(od.quantity * sp.price) AS totalMoney\n" +
            "FROM\n" +
            "    users u\n" +
            "        JOIN\n" +
            "    orders o ON u.id = o.customer_id\n" +
            "        JOIN\n" +
            "    order_details od ON o.id = od.order_id\n" +
            "        JOIN\n" +
            "    sku_products sp ON od.sku_id = sp.id\n" +
            "        JOIN\n" +
            "    products p ON sp.product_id = p.id\n" +
            "        JOIN\n" +
            "    categories c ON p.category_id = c.id\n" +
            "WHERE\n" +
            "    sp.status = 'Còn'\n" +
            "  AND o.booking_date BETWEEN :startDate AND :endDate\n" +
            "GROUP BY\n" +
            "    o.id, u.name, p.name, c.name;")
    Page<OrderDetailDTO> findAllByDate(String startDate, String endDate, Pageable pageable);
}


