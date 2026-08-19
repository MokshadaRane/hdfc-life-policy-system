package com.hdfclife.strategy;

public class TermPremiumStrategy implements PremiumStrategy{
    @Override
    public int calculatePremium(int premium) {
        return (premium * 100) / 100;
    }
}
