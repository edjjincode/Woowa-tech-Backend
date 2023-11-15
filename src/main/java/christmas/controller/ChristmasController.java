package christmas.controller;

import christmas.domain.*;
import christmas.validator.Validator;
import christmas.view.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChristmasController {

    private final User user;

    public ChristmasController(User user) {
        this.user = user;
    }

    public void run() {
        try {
            start();
        } catch(IllegalArgumentException e) {
            Validator.printErrorMessage(e.getMessage());
        }
    }

    public void start(){
        SystemPrintGreeting.printGreeting();
        getDate();
        getOrder();
        implementGame();
    }

    private void getDate() {
        try {
            String date = SystemInput.readDate();
            validateNumDate(date);
            int visitDate = Integer.parseInt(date);
            validateDate(visitDate);
            user.setVisitDate(visitDate);
        } catch (IllegalArgumentException e) {
            Validator.printErrorMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            getDate();
        }
    }

    private void getOrder() {
        String orderMenuPrice = SystemInput.readOrder();
        processOrderInput(orderMenuPrice);
    }

    private void implementGame() {
        int visitDate = user.getVisitDate();
        Map<Menu, Integer> orderMap = user.getOrderMap();
        Payment payment = new Payment();
        Calculator.calculateDiscount(visitDate, orderMap, payment);
        printProgram(visitDate, orderMap, payment);
    }

    public static void printProgram(int visitDate, Map<Menu, Integer> orderMap, Payment payment) {
        SystemDateOutput.printDate(visitDate);
        SystemQuantityOutput.printQuantity(orderMap);
        SystemQuantityOutput.printTotalQuantity(payment);
        SystemQuantityOutput.printGift(payment);
        SystemQuantityOutput.printBenefit(visitDate, orderMap, payment);
        SystemBadgeOutput.printBadge(payment);
    }

    private void processOrderInput(String orderMenuPrice) {
        List<Menu> menuOrderList = new ArrayList<>();
        String[] items = orderMenuPrice.split(",");
        try {
            for (String item : items) {
                addMenuQuantity(item, menuOrderList);
            }
            validateDuplicate(menuOrderList);
            putOrderToUser(menuOrderList);
        } catch (IllegalArgumentException e) {
            Validator.printErrorMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            getOrder();
        }
    }

    private void addMenuQuantity(String item, List<Menu> menuOrderList) {
        validateOrderFormat(item);
        String[] parts = item.split("-");
        Menu menu = getMenuByName(parts[0]);
        menuOrderList.add(menu);
        int quantity = Integer.parseInt(parts[1]);
        user.addToOrder(menu, quantity);
    }

    private void putOrderToUser(List<Menu> menuOrderList) {
        Set<Menu> orderSet = new HashSet<>(menuOrderList);
        if ((checkOnlyDrink(orderSet) && orderSet.size() == 1) || calculateOrderTotal() >= 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private int calculateOrderTotal() {
        Map<Menu, Integer> orderMap = user.getOrderMap();
        int total = 0;
        for (int quantity : orderMap.values()) {
            total += quantity;
        }
        return total;
    }

    public static void validateDate(int visitDate){
        if (visitDate < 1 && visitDate > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateNumDate(String date) {
        Pattern pattern = Pattern.compile("\\D");
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equalsIgnoreCase(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }



}

