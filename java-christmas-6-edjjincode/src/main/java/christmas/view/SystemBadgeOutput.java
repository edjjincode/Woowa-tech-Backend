package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Payment;
import christmas.domain.User;
import java.text.DecimalFormat;

public class SystemBadgeOutput {
    private static final String PRINT_AFTER_DISCOUNT = "\n<할인 후 예상 결제 금액>";
    private static final String DECIMAL_FORMAT = "#,###";
    private static final String WON = "원";

    private static final String DECEMBER_EVENT = "\n<12월 이벤트 배지>";
    public static void printBadge(Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        System.out.println(PRINT_AFTER_DISCOUNT);
        System.out.println(decimalFormat.format(payment.getFinalPayment()) + WON);

        System.out.println(DECEMBER_EVENT);
        System.out.println(Badge.getBadgeByTotalDiscount(payment.getTotalBenefit()));

    }
}
