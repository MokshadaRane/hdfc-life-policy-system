package com.hdfclife.strategy;

public class EndowmentPremiumStrategy implements PremiumStrategy{
    @Override
    public double calculatePremium(double premium) {
        return (premium * 108) / 100;
    }
}
