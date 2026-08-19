package com.hdfclife.strategy;

public class UlipPremiumStrategy implements PremiumStrategy{
    @Override
    public int calculatePremium(int premium) {
        return (premium * 112) / 100;
    }
}
