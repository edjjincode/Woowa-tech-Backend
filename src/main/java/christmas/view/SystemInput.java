package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class SystemInput {
    public static Integer readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (1~30): ");
        String date = Console.readLine();
        return Integer.parseInt(date);
    }
}
