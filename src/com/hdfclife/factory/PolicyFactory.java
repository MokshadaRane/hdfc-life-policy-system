package com.hdfclife.factory;

import com.hdfclife.exception.UnknownPolicyTypeException;
import com.hdfclife.model.EndowmentPolicy;
import com.hdfclife.model.Policy;
import com.hdfclife.model.TermLifePolicy;
import com.hdfclife.model.UlipPolicy;

public class PolicyFactory {
    public static Policy createPolicy(String policyNo, String customer, String type, double premium, String status){
        return switch (type) {
            case "ENDOWMENT" -> new EndowmentPolicy(policyNo, customer, type, premium, status);
            case "TERM" -> new TermLifePolicy(policyNo, customer, type, premium, status);
            case "ULIP" -> new UlipPolicy(policyNo, customer, type, premium, status);
            default -> throw new UnknownPolicyTypeException("Unknown policy type");
        };
    };
}
