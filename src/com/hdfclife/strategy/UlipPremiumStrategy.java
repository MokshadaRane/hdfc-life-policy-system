package com.hdfclife.strategy;

public class UlipPremiumStrategy implements PremiumStrategy{
    @Override
    public double calculatePremium(double premium) {
        return (premium * 112) / 100;
    }
}
