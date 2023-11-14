package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.Validator;

public class SystemInput {
    public static Integer readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (1~30): ");
        String date = Console.readLine();
        int visitDate = Integer.parseInt(date);
        try {
            validateDate(visitDate);
        }
        catch (IllegalArgumentException e) {
            Validator.printErrorMessage(e.getMessage());
            readDate();
        }
        return visitDate;
    }

    public static void validateDate(int visitDate){
        if (visitDate >= 1 && visitDate <= 30) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g., 해산물파스타-2,레드와인-1,초코케이크-1): ");
        String menuPrice = Console.readLine();
        return menuPrice;
    }
}
