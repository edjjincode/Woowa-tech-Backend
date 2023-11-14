package christmas.view;

import christmas.controller.ChristmasController;
import christmas.domain.Discount;
import christmas.domain.User;

import java.text.DecimalFormat;

public class SystemBadgeOutput {
    public static void printBadge(User user) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(decimalFormat.format(user.getFinalPayment()) + "원");

        System.out.println("\n<12월 이벤트 배지>");
        if (user.getTotalDiscount() > 0) {
            System.out.println(getBadgeByTotalDiscount(user.getTotalBenefit()));
        } else {
            System.out.println("없음");
        }
    }
    private static String getBadgeByTotalDiscount(int totalBenefit) {
        if (totalBenefit >= 20_000) {
            return "산타";
        } else if (totalBenefit >= 10_000) {
            return "트리";
        } else if (totalBenefit >= 5_000) {
            return "별";
        } else {
            return "없음";
        }
    }
}
