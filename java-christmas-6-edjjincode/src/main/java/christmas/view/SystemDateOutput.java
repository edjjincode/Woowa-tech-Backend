package christmas.view;

import christmas.domain.User;
public class SystemDateOutput {
    private static final String DECEMBER = "\n12월 ";
    private static final String EVENT_PRIVILEGE = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    public static void printDate(int visitDate) {
        System.out.println(DECEMBER + visitDate + EVENT_PRIVILEGE);
    }
}
