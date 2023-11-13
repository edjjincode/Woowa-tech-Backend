package christmas.controller;

import christmas.domain.*;
import christmas.view.SystemInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasControllerTest {

    @Test
    void checkOrderMenuPrice() {
//        User user = new User();
//        String orderMenuPrice = "해산물파스타-2";
//        String[] items = orderMenuPrice.split(",");
//        for (String item : items) {
//            String[] parts = item.split("-");
//            int quantity = Integer.parseInt(parts[1]);
//            Menu menu = parts[0]
//            user.addToOrder(parts[0], quantity);
        for (Menu menu : Menu.values()) {
            System.out.println(menu.getName());
        }

    }

    @DisplayName("getMenuByName 제대로 작동하는지 확인하기")
    @Test
    void checkGetMenuByName() {
        Menu menu = ChristmasController.getMenuByName("양송이수프");
        assertEquals(Menu.MUSHROOM_SOUP, menu);
    }

    @DisplayName("calculateChampagne이 제대로 작동하는 지 확인하기")
    @Test
    void checkChampagne() {
        User user = new User();
        user.setVisitDate(1);
        user.addToOrder(Menu.T_BONE_STEAK, 3);
        Calculator.calculateDiscount(user);
        if (ChristmasController.calculateChampagne(user)) {
            System.out.println("Y");
        }
    }

    @DisplayName("processOrderInput 함수 제대로 작동하는지 확인하기")
    @Test
    void checkProcessOrderInput() {
        String input = "T_BONE_STEAK-2,CHOCO_CAKE-1";
        String[] items = input.split(",");
        for (String item : items) {
            String[] parts = item.split("-");
            System.out.println(parts[0]);
            System.out.println(parts[1]);
        }
    }
    @DisplayName("checkOnlyDrink메서드가 제대로 작동하는지 확인하기")
    @Test
    void checkTestOnlyDrink() {
        User user = new User();
        user.addToOrder(Menu.RED_WINE, 3);
        Set<Menu> orderSet = user.getOrderMap().keySet();
        assertEquals(ChristmasController.checkOnlyDrink(orderSet), true);
    }

}