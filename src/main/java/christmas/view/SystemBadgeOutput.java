package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Payment;
import christmas.domain.User;
import java.text.DecimalFormat;

public class SystemBadgeOutput {
    public static void printBadge(Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(decimalFormat.format(payment.getFinalPayment()) + "원");

        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(Badge.getBadgeByTotalDiscount(payment.getTotalBenefit()));

    }
}
