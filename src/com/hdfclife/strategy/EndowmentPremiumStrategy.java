package com.hdfclife.strategy;

public class EndowmentPremiumStrategy implements PremiumStrategy{
    @Override
    public int calculatePremium(int premium) {
        return (premium * 108) / 100;
    }
}
