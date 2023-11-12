package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class SystemInput {
    public static Integer readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (1~30): ");
        String date = Console.readLine();
        return Integer.parseInt(date);
    }

    public static String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g., 해산물파스타-2,레드와인-1,초코케이크-1): ");
        String menuPrice = Console.readLine();
        return menuPrice;
    }
}
