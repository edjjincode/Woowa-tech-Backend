package christmas.domain;

public class Payment {
    private int totalOrderAmount;
    private int totalDiscount;
    private int totalBenefit;
    private int finalPayment;

    public Payment() {
        this.totalOrderAmount = totalOrderAmount;
        this.totalDiscount = totalDiscount;
        this.totalBenefit = totalBenefit;
        this.finalPayment = finalPayment;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getFinalPayment() {
        return finalPayment;
    }
    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }
    public int getTotalBenefit() {return totalBenefit; }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setFinalPayment(int finalPayment) {
        this.finalPayment = finalPayment;
    }

    public void setTotalOrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
    public void setTotalBenefit(int totalBenefit) {this.totalBenefit = totalBenefit;}
}


