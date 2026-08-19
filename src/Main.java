import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.InvalidClaimException;
import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.exception.UnknownPolicyTypeException;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;
import com.hdfclife.model.Urgency;
import com.hdfclife.observer.BranchLetterNotifier;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.observer.InAppNotifier;
import com.hdfclife.service.AuditLogger;
import com.hdfclife.service.ClaimService;
import com.hdfclife.store.PolicyStore;
import com.hdfclife.strategy.PremiumCalculator;
import com.hdfclife.strategy.TermPremiumStrategy;
import com.hdfclife.strategy.UlipPremiumStrategy;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    AuditLogger auditLogger = new AuditLogger();

    System.out.println(AppConfig.INSTANCE.getCompanyName());

    PolicyStore ps = new PolicyStore();

    Policy p1 = PolicyFactory.createPolicy("HDFC-LIFE-1001", "Anita Sharma", "TERM", 18500, "Active");
    ps.addPolicy(p1);

    Policy p2 = PolicyFactory.createPolicy("HDFC-LIFE-1002", "Rahul Mehta", "ULIP", 42000, "Active");
    ps.addPolicy(p2);

    Policy p3 = PolicyFactory.createPolicy("HDFC-LIFE-1003", "Priya Nair", "ENDOWMENT", 27000, "Lapsed");
    ps.addPolicy(p3);

    Policy p5 = PolicyFactory.createPolicy("HDFC-LIFE-1005", "Sneha Patel", "ULIP", 36000, "Active");
    ps.addPolicy(p5);

    Policy p4 = PolicyFactory.createPolicy("HDFC-LIFE-1004", "Vikram Singh", "TERM", 15200, "Active");
    ps.addPolicy(p4);


    Policy p6 = PolicyFactory.createPolicy("HDFC-LIFE-1006", "Anita Sharma", "ENDOWMENT", 22000, "Pending");
    ps.addPolicy(p6);

    List<Policy> policies = ps.getAllPolicies();
    Iterator<Policy> it = policies.iterator();
    while (it.hasNext()){
        System.out.println(it.next());
    }

    System.out.println("Unique Customer count: " + ps.getUniqueCustomers().size());

    Policy p1004 = ps.findPolicy("HDFC-LIFE-1004");
    System.out.println("Lookup HDFC-LIFE-1004: " + p1004.getCustomer());

    System.out.println("TreeMap keys in sorted order");
    for(String key : ps.getSortedPolicies().keySet()){
        System.out.println(key);
    }

    PremiumCalculator premiumCalculatorTerm = new PremiumCalculator(new UlipPremiumStrategy());
    double p2Premium = premiumCalculatorTerm.calculatePremium(p2.getPremium());
    System.out.println("Ulip Premium for HDFC-LIFE-1002 " + p2Premium);

    ClaimEventPublisher publisher = new ClaimEventPublisher();
    publisher.registerClaimObserver(new InAppNotifier());
    publisher.registerClaimObserver(new BranchLetterNotifier());

    ClaimService claimService = new ClaimService(ps, publisher);

    Claim highClaim = new Claim.ClaimBuilder("HDFC-LIFE-1001", 25000, Urgency.HIGH)
                            .hospitalName("Apollo Hospital")
                            .remark("Hospitalisation")
                            .build();

    auditLogger.log(highClaim);

    claimService.fileClaim(highClaim);
    claimService.updateStatus(highClaim, "APPROVED");

    Claim mediumClaim = new Claim.ClaimBuilder("HDFC-LIFE-1002", 30000, Urgency.MEDIUM)
            .hospitalName("Apollo Hospital")
            .remark("Hospitalisation")
            .build();

    auditLogger.log(mediumClaim);

    claimService.fileClaim(mediumClaim);

    Claim lowClaim = new Claim.ClaimBuilder("HDFC-LIFE-1003", 40000, Urgency.LOW)
            .hospitalName("Apollo Hospital")
            .remark("Hospitalisation")
            .build();

    auditLogger.log(lowClaim);

    claimService.fileClaim(lowClaim);

    System.out.println("PriorityQueue poll order");
    Claim claim;
    while ((claim= ps.pollClaim()) != null){
        System.out.println(claim.getUrgency());
    }

    System.out.println("Missing Policy");
    try{
        ps.findPolicy("HDFC-LIFE-9999");
    } catch (PolicyNotFoundException e) {
        System.out.println("Caught: " + e.getMessage());
    }

    System.out.println("Invalid claim amount: ");
    try{
        Claim invalidClaim = new Claim.ClaimBuilder("HDFC-LIFE-1001", 700000, Urgency.HIGH).build();
        claimService.fileClaim(invalidClaim);
    } catch (InvalidClaimException e){
        System.out.println("Caught: " + e.getMessage());
    }

    System.out.println("Invalid factory type: ");
    try{
        PolicyFactory.createPolicy("HDFC-LIFE-9999", "Test", "INVALID", 15000, "Active");
    } catch (UnknownPolicyTypeException e){
        System.out.println("Caught: " + e.getMessage());
    }

    auditLogger.logToFile();

    System.out.println("audit.log contains filed claim entry");
}
