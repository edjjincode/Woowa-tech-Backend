package christmas.domain;

import java.util.Map;

public class Calculator {

    public static void calculateDiscount(User user) {
        int totalDiscount = 0;
        int totalOrderAmount = 0;

        Map<Menu, Integer> orderMap = user.getOrderMap();

        for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            totalOrderAmount += menu.getPrice() * quantity;
        }

        totalDiscount += calculateChristmasDiscount(user.getVisitDate());
        totalDiscount += calculateWeekdayDiscount(user.getVisitDate(), user.getOrderMap());
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

    private static boolean isDiscountApplicable(int visitDate, Menu menu) {
        boolean isWeekday = DateUtil.isWeekday(visitDate);
        boolean isWeekend = !isWeekday;

        return (isWeekday && MenuType.DESSERT.getFoodList().contains(menu)) ||
                (isWeekend && MenuType.MAIN.getFoodList().contains(menu));
    }





}
