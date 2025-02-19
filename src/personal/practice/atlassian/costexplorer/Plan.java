package personal.practice.atlassian.costexplorer;

class Plan {
    private String planId;
    private double monthlyCost;

    public Plan(String planId, double monthlyCost) {
        this.planId = planId;
        this.monthlyCost = monthlyCost;
    }

    public String getPlanId() {
        return planId;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }
}