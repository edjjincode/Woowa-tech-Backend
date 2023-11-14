package christmas.controller;

import christmas.domain.*;
import christmas.validator.Validator;
import christmas.view.SystemInput;
import christmas.view.SystemOutput;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChristmasController {

    public  void run() {
        try {
            start();
        } catch(IllegalArgumentException e) {
            Validator.printErrorMessage(e.getMessage());
        }
    }
    public void start(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        int visitDate = SystemInput.readDate();

        String orderMenuPrice = SystemInput.readOrder();

        User user = new User();
        user.setVisitDate(visitDate);
        SystemOutput.printDate(user);

        processOrderInput(orderMenuPrice, user);
        Calculator.calculateDiscount(user);
        SystemOutput.printOrderDetails(user);

    }

    public static void processOrderInput(String orderMenuPrice, User user) {
        putOrderQuantity(orderMenuPrice, user);
        Set<Menu> orderSet = user.getOrderMap().keySet();
        if (checkOnlyDrink(orderSet) && orderSet.size() == 1) {
            throw new IllegalArgumentException();
        }
        if(calculateOrderTotal(user) >= 20) {
            throw new IllegalArgumentException();
        }
    }

    public static void putOrderQuantity(String orderMenuPrice, User user) {
        String[] items = orderMenuPrice.split(",");
        for (String item : items) {
            try {
                validateOrderFormat(item);
            } catch (IllegalArgumentException e){
                Validator.printErrorMessage(e.getMessage());
                SystemInput.readOrder();
            }
            String[] parts = item.split("-");
            try {
                Menu menu = getMenuByName(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                user.addToOrder(menu, quantity);
            } catch (IllegalArgumentException e) {
                Validator.printErrorMessage(e.getMessage());
                SystemInput.readOrder();
            }
        }
    }
    public static void validateOrderFormat(String menuPrice) {
        String regex = "([가-힣]+)-(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(menuPrice);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
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

    public static int calculateOrderTotal(User user) {
        Map<Menu, Integer> orderMap = user.getOrderMap();
        int total = 0;

        for (int quantity : orderMap.values()) {
            total += quantity;
        }

        return total;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equalsIgnoreCase(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static boolean calculateChampagne(User user) {
        return user.getTotalDiscount() >= 12_000;
    }


}

