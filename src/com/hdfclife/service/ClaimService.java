package com.hdfclife.service;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.InvalidClaimException;
import com.hdfclife.model.Claim;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.observer.ClaimObserver;
import com.hdfclife.store.PolicyStore;

public class ClaimService {
    PolicyStore policyStore;
    ClaimEventPublisher claimEventPublisher;

    public ClaimService(PolicyStore policyStore, ClaimEventPublisher claimEventPublisher) {
        this.policyStore = policyStore;
        this.claimEventPublisher = claimEventPublisher;
    }

    public void fileClaim(Claim claim){

        policyStore.findPolicy(claim.getPolicyNo());

        if(claim.getClaimAmount() <= 0 || claim.getClaimAmount() > AppConfig.INSTANCE.getMaxClaimAmount()){
            throw new InvalidClaimException("Invalid claim amount: " + claim.getClaimAmount() + ". Maximum allowed is " + AppConfig.INSTANCE.getMaxClaimAmount());
        }

        policyStore.addClaim(claim);
    }

    public void updateStatus(Claim claim, String newStatus){
        claim.updateStatus(newStatus);
        claimEventPublisher.notifyClaimObserver(claim);
    }
}
