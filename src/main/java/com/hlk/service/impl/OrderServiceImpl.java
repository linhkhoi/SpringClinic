/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Order;
import com.hlk.repository.OrderRepository;
import com.hlk.service.OrderService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrders(String kw) {
        return this.orderRepository.getOrders(kw);
    }

    @Override
    public Order getOrderById(int orderId) {
        return this.orderRepository.getOrderById(orderId);
    }

    @Override
    public boolean deleteOrder(int orderId) {
        return this.orderRepository.deleteOrder(orderId);
    }

    @Override
    public boolean addOrUpdateOrder(Order order) {
        return this.orderRepository.addOrUpdateOrder(order);
    }

    @Override
    public List<List<Map<Object, Object>>> getCountSalesByMonth(int month, int year) {
        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
        
        List<Object[]> rs = this.orderRepository.getCountSalesByMonth(month, year);
         for (Object[] obj: rs) {
            map = new HashMap<Object,Object>(); 
            map.put("x", obj[0]); 
            map.put("y", obj[1]);
            dataPoints1.add(map);
        }
        list.add(dataPoints1);
        
        return list;
    }

    @Override
    public List<List<Map<Object, Object>>> getCountSalesByQuarter(int quarter, int year) {
        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
        
        List<Object[]> rs = this.orderRepository.getCountSalesByQuarter(quarter, year);
         for (Object[] obj: rs) {
            map = new HashMap<Object,Object>(); 
            map.put("x", obj[0]); 
            map.put("y", obj[1]);
            dataPoints1.add(map);
        }
        list.add(dataPoints1);
        
        return list;
    }

    @Override
    public List<List<Map<Object, Object>>> getCountSalesByYear(int year) {
        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
        
        List<Object[]> rs = this.orderRepository.getCountSalesByYear(year);
         for (Object[] obj: rs) {
            map = new HashMap<Object,Object>(); 
            map.put("x", obj[0]); 
            map.put("y", obj[1]);
            dataPoints1.add(map);
        }
        list.add(dataPoints1);
        
        return list;
    }

    @Override
    public Map<Object, Object> getStatisticSalesByMonth(int month, int year) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        BigDecimal max= new BigDecimal(0);
        BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal total = new BigDecimal(0);
        List<Object[]> rs = this.orderRepository.getCountSalesByMonth(month, year);
        for (Object[] obj: rs) {
            if(max.compareTo((BigDecimal) obj[1]) == -1 ){
                max = (BigDecimal) obj[1];
                map.put("max", max.longValue()); 
                map.put("maxDay", obj[0]);
            }
            if(min.compareTo((BigDecimal) obj[1]) == 1 ){
                min = (BigDecimal) obj[1];
                map.put("min", min.longValue()); 
                map.put("minDay", obj[0]);
            }
            BigDecimal a = new BigDecimal(0);
            total = a.add((BigDecimal) obj[1]);
        }
        map.put("total", total.longValue());
        
        return map;
    }

    @Override
    public Map<Object, Object> getStatisticSalesByQuarter(int quarter, int year) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        BigDecimal max= new BigDecimal(0);
        BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal total = new BigDecimal(0);
        List<Object[]> rs = this.orderRepository.getCountSalesByQuarter(quarter, year);
        for (Object[] obj: rs) {
            if(max.compareTo((BigDecimal) obj[1]) == -1 ){
                max = (BigDecimal) obj[1];
                map.put("max", max.longValue()); 
                map.put("maxMonth", obj[0]);
            }
            if(min.compareTo((BigDecimal) obj[1]) == 1 ){
                min = (BigDecimal) obj[1];
                map.put("min", min.longValue()); 
                map.put("minMonth", obj[0]);
            }
            BigDecimal a = new BigDecimal(0);
            total = a.add((BigDecimal) obj[1]);
        }
        map.put("total", total.longValue());
        
        return map;
    }

    @Override
    public Map<Object, Object> getStatisticSalesByYear(int year) {
       Map<Object, Object> map = new HashMap<Object, Object>();
        BigDecimal max= new BigDecimal(0);
        BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal total = new BigDecimal(0);
        List<Object[]> rs = this.orderRepository.getCountSalesByYear(year);
        for (Object[] obj: rs) {
            if(max.compareTo((BigDecimal) obj[1]) == -1 ){
                max = (BigDecimal) obj[1];
                map.put("max", max.longValue()); 
                map.put("maxMonth", obj[0]);
            }
            if(min.compareTo((BigDecimal) obj[1]) == 1 ){
                min = (BigDecimal) obj[1];
                map.put("min", min.longValue()); 
                map.put("minMonth", obj[0]);
            }
            total = total.add((BigDecimal) obj[1]);
        }
        map.put("total", total.longValue());
        
        return map;
    }

    @Override
    public List<Order> getOrders(String kw, Date fromDate, Date toDate, int page) {
        return this.orderRepository.getOrders(kw, fromDate, toDate, page);
    }

    @Override
    public long countOrder(String kw, Date fromDate, Date toDate) {
        return this.orderRepository.countOrder(kw, fromDate, toDate);
    }
    
}
