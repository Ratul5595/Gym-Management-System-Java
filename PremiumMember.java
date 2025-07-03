public class PremiumMember extends GymMember {
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    public PremiumMember(int id, String name, String location, String phone, String email,
                        String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.premiumCharge = 50000;
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Accessor methods
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean isFullPayment() {
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    // Payment method
    public String payDueAmount(double payment) {
        if (isFullPayment) {
            return "Payment already completed. No further payments required.";
        }

        if (payment <= 0) {
            return "Invalid payment amount. Amount must be positive.";
        }

        if (paidAmount + payment > premiumCharge) {
            return "Payment exceeds the total premium charge. Please pay exactly " + (premiumCharge - paidAmount);
        }

        paidAmount += payment;
        
        if (paidAmount == premiumCharge) {
            isFullPayment = true;
            calculateDiscount();
            return "Payment completed successfully. Thank you for your full payment!";
        } else {
            double remainingAmount = premiumCharge - paidAmount;
            return "Payment of " + payment + " received. Remaining amount: " + remainingAmount;
        }
    }

    // Discount calculation
    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10;
            System.out.println("Discount calculated: " + discountAmount);
        } else {
            discountAmount = 0;
            System.out.println("No discount available. Complete payment to get 10% discount.");
        }
    }

    // Revert method
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    @Override
    public void markAttendance() {
        if (super.activeStatus) {
            super.attendance++;
            super.loyaltyPoints += 10; // Premium members get more points
        }
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Member Type: Premium");
        System.out.println("Premium Charge: " + premiumCharge);
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment Status: " + (isFullPayment ? "Completed" : "Pending"));
        
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount to Pay: " + remainingAmount);
        
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}