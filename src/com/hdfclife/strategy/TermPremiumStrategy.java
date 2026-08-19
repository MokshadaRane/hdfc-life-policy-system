package com.hdfclife.strategy;

public class TermPremiumStrategy implements PremiumStrategy{
    @Override
    public double calculatePremium(double premium) {
        return (premium * 100) / 100;
    }
}
