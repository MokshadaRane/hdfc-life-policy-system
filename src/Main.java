import com.hdfclife.config.AppConfig;
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

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    System.out.println(AppConfig.INSTANCE.getCompanyName());
    System.out.println(AppConfig.INSTANCE.getMaxClaimAmount());

    PolicyStore ps = new PolicyStore();

    Policy p1 = PolicyFactory.createPolicy("HDFC-LIFE-1001", "Anita Sharma", "TERM", 18500, "Active");
    ps.addPolicy(p1);

    Policy p2 = PolicyFactory.createPolicy("HDFC-LIFE-1002", "Rahul Mehta", "ULIP", 42000, "Active");
    ps.addPolicy(p2);

    Policy p3 = PolicyFactory.createPolicy("HDFC-LIFE-1003", "Priya Nair", "ENDOWMENT", 27000, "Lapsed");
    ps.addPolicy(p3);

    Policy p4 = PolicyFactory.createPolicy("HDFC-LIFE-1004", "Vikram Singh", "TERM", 15200, "Active");
    ps.addPolicy(p4);

    Policy p5 = PolicyFactory.createPolicy("HDFC-LIFE-1005", "Sneha Patel", "ULIP", 36000, "Active");
    ps.addPolicy(p5);

    Policy p6 = PolicyFactory.createPolicy("HDFC-LIFE-1006", "Anita Sharma", "ENDOWMENT", 22000, "Pending");
    ps.addPolicy(p6);

    List<Policy> policies = ps.getAllPolicies();
//    Iterator<Policy> it = policies.iterator();
//    while (it.hasNext()){
//        System.out.println(it.next());
//    }

    AuditLogger auditLogger = new AuditLogger();
    for (Policy policy : policies) {
        auditLogger.log(policy);
        System.out.println(policy);
    }

    PremiumCalculator premiumCalculatorTerm = new PremiumCalculator(new TermPremiumStrategy());
    double p1Premium = premiumCalculatorTerm.calculatePremium(p1.getPremium());
    System.out.println("Premium for p1 " + p1Premium);

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

    auditLogger.logToFile();

}
