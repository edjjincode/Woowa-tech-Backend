package christmas.controller;

import christmas.domain.Calculator;
import christmas.domain.Discount;
import christmas.domain.User;
import christmas.view.SystemInput;
import christmas.domain.Menu;

public class ChristmasController {

    public static void run() {
        int visitDate = SystemInput.readDate();
        User user = new User();
        String orderMenuPrice = SystemInput.readOrder();
        if (checkRunGame(orderMenuPrice)) {
            processOrderInput(orderMenuPrice, user);
            Calculator.calculateDiscount(user);
            if (calculateChampagne(user)) {
                user.setTotalBenefit(user.getTotalDiscount() + Discount.CHAMPAGNE_DISCOUNT.getValue());
            } else {
                user.setTotalBenefit(user.getTotalDiscount());
            }
        } else {
            System.out.println("[ERROR] 주문 금액이 너무 낮습니다. 최소 주문 금액은 10,000원입니다.");
        }
    }
    public static void processOrderInput(String orderMenuPrice, User user) {
        String[] items = orderMenuPrice.split(",");
        for (String item : items) {
            String[] parts = item.split("-");
            Menu menu = getMenuByName(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            user.addToOrder(menu, quantity);
        }
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equalsIgnoreCase(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static boolean calculateChampagne(User user) {
        return user.getTotalDiscount() >= 12_000;
    }

    private static boolean checkRunGame(String totalOrderAmount) {
        return Integer.parseInt(totalOrderAmount) >= 10_000;
    }
}

