package com.hdfclife.model;

public class Policy {
    private String policyNo;
    private String customer;
    private String type;
    private double premium;
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyNo='" + policyNo + '\'' +
                ", customer='" + customer + '\'' +
                ", type='" + type + '\'' +
                ", premium=" + premium +
                ", status='" + status + '\'' +
                '}';
    }

    public Policy(String policyNo, String customer, String type, double premium, String status) {
        this.type = type;
        this.policyNo = policyNo;
        this.customer = customer;
        this.premium = premium;
        this.status = status;
    }
}
