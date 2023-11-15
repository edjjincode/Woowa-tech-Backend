package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int visitDate;
    private Map<Menu, Integer> orderMap;
    private Payment payment;

    public User(Payment payment) {
        this.payment = payment;
        this.orderMap = new HashMap<>();
    }

    public void addToOrder(Menu menu, int quantity) {
        if (!Menu.isValidMenu(menu) || quantity <= 0) {
            throw new IllegalArgumentException();
        }

        orderMap.put(menu, quantity);
    }

    public void updatePayment(int totalDiscount, int totalOrderAmount) {
        payment.setTotalDiscount(totalDiscount);
        payment.setTotalOrderAmount(totalOrderAmount);
        payment.setFinalPayment(totalOrderAmount - totalDiscount);
        if (!Calculator.calculateChampagne(payment)) {
            payment.setTotalBenefit(totalDiscount);
        }
        payment.setTotalBenefit(totalDiscount + Discount.CHAMPAGNE_DISCOUNT.getValue());
    }

    public int getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public Map<Menu, Integer> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Menu, Integer> orderMap) {
        this.orderMap = orderMap;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

