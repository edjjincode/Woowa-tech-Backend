package christmas.domain;

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
        if (Menu.isValidMenu(menu) && quantity > 0) {
            orderMap.put(menu, quantity);
        }
        else {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int getVisitDate() {
        return visitDate;
    }

    public Map<Menu, Integer> getOrderMap() {
        return orderMap;
    }



}
