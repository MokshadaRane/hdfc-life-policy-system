package com.hdfclife.observer;

import com.hdfclife.model.Claim;

public class InAppNotifier implements ClaimObserver{

    @Override
    public void onClaimUpdate(Claim claim) {
        System.out.println("InAppNotifier: Claim " + claim.getPolicyNo() + " status is updated to " + claim.getStatus());
    }
}
