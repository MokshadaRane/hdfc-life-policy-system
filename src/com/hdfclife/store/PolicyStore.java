package com.hdfclife.store;

import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;

import java.util.*;

public class PolicyStore {
    List<Policy> allPolicies = new ArrayList<>();
    HashSet<String> customerName = new HashSet<>();
    HashMap<String, Policy> policyMap = new HashMap<>();
    TreeMap<String, Policy> policyTree = new TreeMap<>();

    PriorityQueue<Claim> claims = new PriorityQueue<>(
            new Comparator<Claim>() {
                @Override
                public int compare(Claim c1, Claim c2) {
                    return Integer.compare(c1.getUrgency().getPriority(), c2.getUrgency().getPriority());
                }
            }
    );

    public void addPolicy(Policy policy){
        allPolicies.add(policy);
        customerName.add(policy.getCustomer());
        policyMap.put(policy.getPolicyNo(), policy);
        policyTree.put(policy.getPolicyNo(), policy);
    }

    public List<Policy> getAllPolicies(){
        return allPolicies;
    }

    public Policy findPolicy(String policyNo){

        Policy policy = policyMap.get(policyNo);

        if(policy == null){
            throw new PolicyNotFoundException("Policy not found " + policyNo);
        }

        return policy;
    }

    public void addClaim(Claim claim){
        claims.offer(claim);
    }

    public Claim pollClaim(){
        return claims.poll();
    }

}
