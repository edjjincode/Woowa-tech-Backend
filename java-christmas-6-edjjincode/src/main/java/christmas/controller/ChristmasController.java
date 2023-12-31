package christmas.controller;

import christmas.domain.*;
import christmas.validator.Validator;
import christmas.view.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChristmasController {

    private final User user;

    private static final String WRONG_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String WRONG_INPUT = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String STRING_SEPERATOR = ",";
    private static final String STRING_MIDDLE_SEPERATOR = "-";
    private static final String NUM_VALIDATE = "\\D";
    private static final String INPUT_PATTERN = "([가-힣]+)-(\\d+)";
    private static final int MAX_NUM = 20;



    public ChristmasController(User user) {
        this.user = user;
    }

    public void run() {
        try {
            start();
        } catch (IllegalArgumentException e) {
            Validator.printErrorMessage(e.getMessage());
        }
    }

    public void start() {
        SystemPrintGreeting.printGreeting();
        getDate();
        getOrder();
        Calculator.calculateDiscount(user);
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
            Validator.printErrorMessage(WRONG_DATE);
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
        Payment payment = user.getPayment();
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

    public static void validateNumDate(String date) {
        Pattern pattern = Pattern.compile(NUM_VALIDATE);
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()){
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }

    public static void validateDate(int visitDate){
        if (visitDate < 1 && visitDate > 31) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }

    private void processOrderInput(String orderMenuPrice) {
        List<Menu> menuOrderList = new ArrayList<>();
        String[] items = orderMenuPrice.split(STRING_SEPERATOR);
        try {
            for (String item : items) {
                addMenuQuantity(item, menuOrderList);
            }
            validateDuplicate(menuOrderList);
            putOrderToUser(menuOrderList);
        } catch (IllegalArgumentException e) {
            Validator.printErrorMessage(WRONG_INPUT);
            getOrder();
        }
    }

    private void addMenuQuantity(String item, List<Menu> menuOrderList) {
        validateOrderFormat(item);
        String[] parts = item.split(STRING_MIDDLE_SEPERATOR);
        Menu menu = getMenuByName(parts[0]);
        menuOrderList.add(menu);
        int quantity = Integer.parseInt(parts[1]);
        user.addToOrder(menu, quantity);
    }

    private void putOrderToUser(List<Menu> menuOrderList) {
        Set<Menu> orderSet = new HashSet<>(menuOrderList);
        if ((checkOnlyDrink(orderSet) && orderSet.size() == 1) || calculateOrderTotal() >= MAX_NUM) {
            throw new IllegalArgumentException(WRONG_INPUT);
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
        throw new IllegalArgumentException(WRONG_INPUT);
    }

    public static void validateDuplicate(List<Menu> menuList) {
        HashSet setMenu = new HashSet(menuList);
        if (setMenu.size() != menuList.size()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOrderFormat(String menuPrice) {
        String regex = INPUT_PATTERN;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(menuPrice);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }

}



