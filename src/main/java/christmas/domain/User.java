package christmas.domain;

import christmas.controller.ChristmasController;
import christmas.validator.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int visitDate;
    private Map<Menu, Integer> orderMap;
    public User() {
        this.orderMap = new HashMap<>();
    }


    public void addToOrder(Menu menu, int quantity) {
        if (!Menu.isValidMenu(menu) || quantity <= 0) {
            throw new IllegalArgumentException();
        }

        orderMap.put(menu, quantity);
    }

    public int getVisitDate() {
        return visitDate;
    }

    public Map<Menu, Integer> getOrderMap() {
        return orderMap;
    }
    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }


}
