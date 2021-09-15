/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.utils;

import java.math.BigDecimal;

/**
 *
 * @author MSIGE66
 */
public enum PriceList {
    SANG_NGAY_THUONG {
        @Override
        public BigDecimal layGiaSanh() {
            
            return new BigDecimal(100000);
        }

        @Override
        public String toString() {
            return "Sang ngay thuong";
        }   
    },
    TOI_NGAY_THUONG {
        @Override
        public BigDecimal layGiaSanh() {
            return new BigDecimal(150000);
        }

        @Override
        public String toString() {
            return "Toi ngay thuong";
        }

    },
    SANG_CUOI_TUAN {
        @Override
        public BigDecimal layGiaSanh() {
            
            return new BigDecimal(150000);
        }

        @Override
        public String toString() {
            return "Sang cuoi tuan";
        }

    },
    TOI_CUOI_TUAN {
        @Override
        public BigDecimal layGiaSanh() {
            return new BigDecimal(200000);
        }
        @Override
        public String toString() {
            return "Toi cuoi tuan";
        }

    };
    public abstract BigDecimal layGiaSanh();
}
