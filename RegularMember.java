public class RegularMember extends GymMember {
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    public RegularMember(int id, String name, String location, String phone, String email,
                        String gender, String DOB, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.attendanceLimit = 30;
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    // Accessor methods
    public int getAttendanceLimit() { return attendanceLimit; }
    public boolean isEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }

    @Override
    public void markAttendance() {
        if (super.activeStatus) {
            super.attendance++;
            super.loyaltyPoints += 5;
            
            // Check if member becomes eligible for upgrade
            if (super.attendance >= attendanceLimit) {
                isEligibleForUpgrade = true;
            }
        }
    }

    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1; // Invalid plan
        }
    }

    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Member is not eligible for plan upgrade yet.";
        }
        
        if (newPlan.equalsIgnoreCase(this.plan)) {
            return "Member is already subscribed to the " + this.plan + " plan.";
        }
        
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected. Please choose basic, standard, or deluxe.";
        }
        
        this.plan = newPlan.toLowerCase();
        this.price = newPrice;
        return "Plan successfully upgraded to " + this.plan + ". New price: " + this.price;
    }

    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        System.out.println("Eligible for Upgrade: " + isEligibleForUpgrade);
        System.out.println("Attendance Limit: " + attendanceLimit);
        if (removalReason != null && !removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}