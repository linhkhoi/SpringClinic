/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Order;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI GE66
 */
public interface OrderRepository {
    List<Order> getOrders(String kw);
    Order getOrderById(int orderId);
    List<Order> getOrders(String kw,Date fromDate, Date toDate,int page);
    long countOrder(String kw,Date fromDate, Date toDate);
    boolean deleteOrder(int orderId);
    boolean addOrUpdateOrder(Order order);
    List<Object[]> getCountSalesByMonth(int month, int year);
    List<Object[]> getCountSalesByQuarter(int quarter, int year);
    List<Object[]> getCountSalesByYear(int year);
}
