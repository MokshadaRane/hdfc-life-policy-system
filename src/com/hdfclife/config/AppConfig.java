package com.hdfclife.config;

public enum AppConfig {
    INSTANCE;

    private String companyName = "HDFC Life";
    private double maxClaimAmount = 500000;

    public String getCompanyName() {
        return companyName;
    }

    public double getMaxClaimAmount() {
        return maxClaimAmount;
    }
}
