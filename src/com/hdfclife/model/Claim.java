package com.hdfclife.model;

public class Claim {
    private final String policyNo;
    private final double claimAmount;
    private final Urgency urgency;
    private final String hospitalName;
    private final String remark;
    private String status;

    private Claim(ClaimBuilder builder){
        this.policyNo = builder.policyNo;
        this.claimAmount = builder.claimAmount;
        this.urgency = builder.urgency;
        this.hospitalName = builder.hospitalName;
        this.remark = builder.remark;
        this.status = builder.status;
    }

    public String getPolicyNo() { return policyNo; }
    public double getClaimAmount() { return claimAmount; }
    public Urgency getUrgency() { return urgency; }
    public String getHospitalName() { return hospitalName; }
    public String getRemark() { return remark; }
    public String getStatus() { return status; }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public static class ClaimBuilder{
        private final String policyNo;
        private final double claimAmount;
        private final Urgency urgency;
        private String hospitalName;
        private String remark;
        private String status = "SUBMITTED";


        public ClaimBuilder(String policyNo, double claimAmount, Urgency urgency) {
            this.policyNo = policyNo;
            this.claimAmount = claimAmount;
            this.urgency = urgency;
        }

        public ClaimBuilder hospitalName(String hospitalName){
            this.hospitalName = hospitalName;
            return this;
        }

        public ClaimBuilder remark(String remark){
            this.remark = remark;
            return this;
        }

        public ClaimBuilder status(String status){
            this.status = status;
            return this;
        }

        public Claim build(){
            return new Claim(this);
        }
    }
}
