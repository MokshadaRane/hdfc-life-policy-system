package com.hdfclife.store;

import com.hdfclife.model.Policy;

import java.util.*;

public class PolicyStore {
    List<Policy> allPolicies = new ArrayList<>();
    HashSet<String> customerName = new HashSet<>();
    HashMap<String, Policy> policyMap = new HashMap<>();
    TreeMap<String, Policy> policyTree = new TreeMap<>();

    public void addPolicy(Policy policy){
        allPolicies.add(policy);
        customerName.add(policy.getCustomer());
        policyMap.put(policy.getPolicyNo(), policy);
        policyTree.put(policy.getPolicyNo(), policy);
    }

    public List<Policy> getAllPolicies(){
        return allPolicies;
    }

}
