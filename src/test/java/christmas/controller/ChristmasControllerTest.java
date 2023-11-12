package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.User;
import christmas.view.SystemInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}