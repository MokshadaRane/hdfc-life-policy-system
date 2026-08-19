package com.hdfclife.model;

public class TermLifePolicy extends Policy{

    public TermLifePolicy(String policyNo, String customer, String type, double premium, String status) {
        super(type, policyNo, customer, premium, status);
    }

}
