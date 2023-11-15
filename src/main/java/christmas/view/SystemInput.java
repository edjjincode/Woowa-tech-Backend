package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemInput {

    private static final String OPEN_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (1~30): ";
    private static final String MENU = "주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g., 해산물파스타-2,레드와인-1,초코케이크-1): ";
    public static String readDate() {
        System.out.println(OPEN_DATE);
        String date = Console.readLine();
        return date;
    }

    public static String readOrder() {
        System.out.println(MENU);
        String menuPrice = Console.readLine();
        return menuPrice;
    }
}
