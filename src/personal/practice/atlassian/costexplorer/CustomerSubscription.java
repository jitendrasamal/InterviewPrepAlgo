package personal.practice.atlassian.costexplorer;


import java.time.LocalDate;

class CustomerSubscription {
    private String customerId;
    private String productName;
    private String planId;
    private LocalDate startDate;

    public CustomerSubscription(String customerId, String productName, String planId, LocalDate startDate) {
        this.customerId = customerId;
        this.productName = productName;
        this.planId = planId;
        this.startDate = startDate;
    }

    public String getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
