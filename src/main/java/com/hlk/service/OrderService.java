/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service;

import com.hlk.model.Order;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI GE66
 */
public interface OrderService {
    List<Order> getOrders(String kw);
    Order getOrderById(int orderId);
    boolean deleteOrder(int orderId);
    boolean addOrUpdateOrder(Order order);
    List<Order> getOrders(String kw,Date fromDate, Date toDate,int page);
    long countOrder(String kw,Date fromDate, Date toDate);
    List<List<Map<Object,Object>>> getCountSalesByMonth(int month, int year);
    List<List<Map<Object,Object>>> getCountSalesByQuarter(int quarter, int year);
    List<List<Map<Object,Object>>> getCountSalesByYear(int year);
    Map<Object, Object> getStatisticSalesByMonth(int month, int year);
    Map<Object, Object> getStatisticSalesByQuarter(int quarter, int year);
    Map<Object, Object> getStatisticSalesByYear(int year);
}
