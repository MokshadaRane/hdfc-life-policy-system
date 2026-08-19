package com.hdfclife.observer;

import com.hdfclife.model.Claim;

import java.util.ArrayList;
import java.util.List;

public class ClaimEventPublisher {
    List<ClaimObserver> claimObservers = new ArrayList<>();

    public void registerClaimObserver(ClaimObserver observer){
        claimObservers.add(observer);
    }

    public void removeClaimObserver(ClaimObserver observer){
        claimObservers.remove(observer);
    }

    public void notifyClaimObserver(Claim claim){
        System.out.println("Claim status Updated successfully for Policy: " + claim.getPolicyNo());
        for(ClaimObserver c : claimObservers){
            c.onClaimUpdate(claim);
        }
    }
}
