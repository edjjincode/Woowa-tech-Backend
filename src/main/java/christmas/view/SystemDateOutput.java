package christmas.view;

import java.text.DecimalFormat;

import christmas.controller.ChristmasController;
import christmas.domain.Calculator;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.User;
public class SystemDateOutput {
    public static void printDate(User user) {
        System.out.println("\n12월 " + user.getVisitDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }
}
