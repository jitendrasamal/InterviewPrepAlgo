package personal.practice.costexplorer;

import java.time.LocalDate;
import java.util.*;


class CostExplorer {
    private final Map<String, Plan> pricingPlans;

    public CostExplorer(List<Plan> plans) {
        this.pricingPlans = new HashMap<>();
        for (Plan plan : plans) {
            pricingPlans.put(plan.getPlanId(), plan);
        }
    }

    public double annualCost(CustomerSubscription subscription, int year) {
        return monthlyCostList(subscription, year).stream().mapToDouble(Double::doubleValue).sum();
    }

    public List<Double> monthlyCostList(CustomerSubscription subscription, int year) {
        List<Double> costList = new ArrayList<>(Collections.nCopies(12, 0.0));

        Plan plan = pricingPlans.get(subscription.getPlanId());
        if (plan == null) return costList;

        LocalDate startDate = subscription.getStartDate();
        int startMonth = (startDate.getYear() == year) ? startDate.getMonthValue() : 1;

        for (int i = startMonth - 1; i < 12; i++) {
            costList.set(i, plan.getMonthlyCost());
        }

        return costList;
    }
}
