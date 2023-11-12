package christmas.controller;

import christmas.domain.User;
import christmas.view.SystemInput;
import christmas.domain.Menu;

public class ChristmasController {
    //날짜를 입력받는다
    //메뉴 및 수량을 입력받느다
    // 입력받은 날짜 및 수량을 기준으로 할인율을 계산한다
    // 주문한 메뉴를 출력한다
    // 할인전 총 주문금액을 출력한다
    // 증정 메뉴 여부를 출력한다
    // 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"으로 보여 주세요.
    // 혜택 여부를 출력한다
    // 할인 후 예상 결제 금액 출력한다
    // 12월 달 예상 금액을 출력한다
    public static void run() {
        int visitDate = SystemInput.readDate();

        User user = new User();
        String orderMenuPrice = SystemInput.readOrder();
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


}
