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

    private static int calculateChristmasDiscount(int visitDate) {
        if (visitDate >= 1 && visitDate <= 25) {
            return (visitDate - 1) * Discount.CHRISTMAS_DISCOUNT.getValue() + 1_000;
        }
        return 0;
    }

    private static int calculateWeekdayDiscount(int visitDate, Map<Menu, Integer> orderMap) {
        int discount = 0;

        for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();

            if (DateUtil.isWeekday(visitDate)&& MenuType.DESSERT.getFoodList().contains(menu)) {
                discount += 2_023 * quantity;
            } else if (DateUtil.isWeekend(visitDate) && MenuType.MAIN.getFoodList().contains(menu)) {
                discount += 2_023 * quantity;
            }
        }

        return discount;
    }





}
