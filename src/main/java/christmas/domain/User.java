package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int visitDate;
    private int totalOrderAmount;
    private int totalDiscount;
    private int totalBenefit;
    private int finalPayment;
    private Map<Menu, Integer> orderMap;
    public User() {
        this.orderMap = new HashMap<>();
    }


    public void addToOrder(Menu menu, int quantity) {
        if (Menu.isValidMenu(menu) && quantity > 0) {
            orderMap.put(menu, quantity);
        }
        else {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getVisitDate() {
        return visitDate;
    }
    public int getFinalPayment() {
        return finalPayment;
    }
    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }
    public int getTotalBenefit() {return totalBenefit; }

    public Map<Menu, Integer> getOrderMap() {
        return orderMap;
    }
    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }
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
