package christmas.controller;

import christmas.domain.*;
import christmas.view.SystemInput;

import java.util.Map;
import java.util.Set;

public class ChristmasController {

    public static void run() {
        int visitDate = SystemInput.readDate();
        User user = new User();
        String orderMenuPrice = SystemInput.readOrder();
        processOrderInput(orderMenuPrice, user);
        Calculator.calculateDiscount(user);
        if (calculateChampagne(user)) {
            user.setTotalBenefit(user.getTotalDiscount() + Discount.CHAMPAGNE_DISCOUNT.getValue());
        } else {
            user.setTotalBenefit(user.getTotalDiscount());
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
        Set<Menu> orderSet = user.getOrderMap().keySet();
        if (checkOnlyDrink(orderSet) && orderSet.size() == 1) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean checkOnlyDrink(Set<Menu> keys) {
        for (Menu key : keys) {
            if (!MenuType.DRINK.getFoodList().contains(key)) {
                return false;
            }
        }
        return true;
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


}

