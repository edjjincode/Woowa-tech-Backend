package christmas.domain;

import java.util.Map;

public class Calculator {

    public static void calculateDiscount(int visitDate, Map<Menu, Integer> orderMap, Payment payment) {
        int totalOrderAmount = calculateTotalOrderAmount(orderMap);
        if (checkRunGame(totalOrderAmount)) {
            int totalDiscount = calculateAllDiscount(visitDate, orderMap);
            setAll(totalDiscount, totalOrderAmount, payment);
        }
    }

    public static int calculateTotalOrderAmount(Map<Menu, Integer> orderMap) {
        int totalOrderAmount = 0;
        for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            totalOrderAmount += menu.getPrice() * quantity;
        }
        return totalOrderAmount;
    }

    public static int calculateAllDiscount(int visitDate, Map<Menu, Integer> orderMap) {
        int totalDiscount = 0;
        totalDiscount += calculateChristmasDiscount(visitDate);
        totalDiscount += calculateWeekdayDiscount(visitDate, orderMap);
        totalDiscount += calculateSpecialDiscount(visitDate);
        return totalDiscount;
    }

    public static int calculateChristmasDiscount(int visitDate) {
        if (visitDate >= 1 && visitDate <= 25) {
            return (visitDate - 1) * Discount.CHRISTMAS_DISCOUNT.getValue() + 1_000;
        }
        return 0;
    }

    public static int calculateWeekdayDiscount(int visitDate, Map<Menu, Integer> orderMap) {
        int discount = 0;
        for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            if (isDiscountApplicable(visitDate, menu)) {
                discount += Discount.WEEKDAY_DISCOUNT.getValue() * quantity;
            }
        }
        return discount;
    }

    public static int calculateSpecialDiscount(int visitDate) {
        boolean isSpecial = DateUtil.isSpecialEvent(visitDate);
        int discount = 0;
        if (isSpecial) {
            discount += Discount.SPECIAL_DISCOUNT.getValue();
        }
        return discount;
    }

    public static void setAll(int totalDiscount, int totalOrderAmount, Payment payment) {
        payment.setTotalDiscount(totalDiscount);
        payment.setTotalOrderAmount(totalOrderAmount);
        payment.setFinalPayment(totalOrderAmount - totalDiscount);
        if (!calculateChampagne(payment)) {
            payment.setTotalBenefit(totalDiscount);
        }
        payment.setTotalBenefit(totalDiscount + Discount.CHAMPAGNE_DISCOUNT.getValue());
    }

    private static boolean isDiscountApplicable(int visitDate, Menu menu) {
        boolean isWeekday = DateUtil.isWeekday(visitDate);
        boolean isWeekend = !isWeekday;

        return (isWeekday && MenuType.DESSERT.getFoodList().contains(menu)) ||
                (isWeekend && MenuType.MAIN.getFoodList().contains(menu));
    }

    private static boolean checkRunGame(int totalOrderAmount) {
        return totalOrderAmount >= 10_000;
    }
    public static boolean calculateChampagne(Payment payment) {
        return payment.getTotalOrderAmount() >= 120_000;
    }
}
