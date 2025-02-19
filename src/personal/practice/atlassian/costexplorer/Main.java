package personal.practice.atlassian.costexplorer;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Plan> plans = Arrays.asList(
                new Plan("BASIC", 9.99),
                new Plan("STANDARD", 49.99),
                new Plan("PREMIUM", 249.99)
        );

        CostExplorer costExplorer = new CostExplorer(plans);

        CustomerSubscription subscription = new CustomerSubscription(
                "c1", "Jira", "BASIC", LocalDate.of(2021, 3, 10)
        );

        System.out.println("Monthly Costs: " + costExplorer.monthlyCostList(subscription, 2021));
        System.out.println("Annual Cost: " + costExplorer.annualCost(subscription, 2021));
    }
}
