package christmas.controller;

import christmas.domain.*;
import christmas.validator.Validator;
import christmas.view.SystemInput;
import christmas.view.SystemOutput;

import java.util.*;
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
        User user = new User();
        getDate(user);
        getOrder(user);
        SystemOutput.printDate(user);
        Calculator.calculateDiscount(user);
        SystemOutput.printOrderDetails(user);
    }

    public static void getDate(User user) {
        int visitDate = SystemInput.readDate();
        user.setVisitDate(visitDate);
    }

    public static void getOrder(User user) {
        String orderMenuPrice = SystemInput.readOrder();
        processOrderInput(orderMenuPrice, user);
    }

    public static void processOrderInput(String orderMenuPrice, User user) {
        putOrderQuantity(orderMenuPrice, user);
        Set<Menu> orderSet = user.getOrderMap().keySet();
        if ((checkOnlyDrink(orderSet) && orderSet.size() == 1) || calculateOrderTotal(user) >= 20) {
            Validator.printErrorMessage("유효하지 않은 주문입니다. 다시 입력해주세요.");
            user.clearOrder();
            getOrder(user);
        }
    }

    public static void putOrderQuantity(String orderMenuPrice, User user) {
        List<Menu> menuOrderList = new ArrayList<>();
        String[] items = orderMenuPrice.split(",");
        try {
            for (String item : items) {
                validateOrderFormat(item);
                String[] parts = item.split("-");
                Menu menu = getMenuByName(parts[0]);
                menuOrderList.add(menu);
                int quantity = Integer.parseInt(parts[1]);
                user.addToOrder(menu, quantity);
            }
            validateDuplicate(menuOrderList);
        } catch (IllegalArgumentException e) {
            Validator.printErrorMessage("유효하지 않은 주문입니다. 다시 입력해주세요.");
            user.clearOrder();
            getOrder(user);
        }

    }

    public static void validateDuplicate(List<Menu> menuList) {
        HashSet setMenu = new HashSet(menuList);
        if (setMenu.size() != menuList.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOrderFormat(String menuPrice) {
        String regex = "([가-힣]+)-(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(menuPrice);
        if (!matcher.matches()) {
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

