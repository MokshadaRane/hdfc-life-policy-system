package com.hdfclife.strategy;


 public class PremiumCalculator {
     public PremiumStrategy strategy;

     public PremiumCalculator(PremiumStrategy strategy) {
         this.strategy = strategy;
     }

     public double calculatePremium(double premium){
         return strategy.calculatePremium(premium);
     }
 }
