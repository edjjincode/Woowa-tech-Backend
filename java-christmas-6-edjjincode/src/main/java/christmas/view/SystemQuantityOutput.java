package christmas.view;

import java.text.DecimalFormat;
import java.util.Map;

import christmas.domain.*;

public class SystemQuantityOutput {
    private static final String MENU = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_TOTAL = "\n<할인 전 총주문 금액>";
    private static final String PRIVILEGE_MENU = "\n<증정 메뉴>";
    private static final String BENEFIT = "\n<혜택 내역>";
    private static final String CHRISTMAS = "크리스마스 디데이 할인";
    private static final String DAY_DISCOUNT = "평일 할인";
    private static final String SPECIAL_DISCOUNT ="특별 할인";
    private static final String TOTAL_BENEFIT ="\n<총혜택 금액>";
    private static final String BONUS ="증정 할인: ";
    private static final String DECIMAL_FORMAT = "#,###";
    private static final String WON = "원";
    private static final String NUM = "개";
    private static final String NONE = "없음";
    private static final String SPACE = " ";
    private static final String DOT_SPACE = ": ";
    public static void printQuantity(Map<Menu, Integer> orderMap) {
        System.out.println(MENU);
        for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(menu.getName() + SPACE + quantity + NUM);
        }
    }

    public static void printTotalQuantity(Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        System.out.println(BEFORE_DISCOUNT_TOTAL);
        System.out.println(decimalFormat.format(payment.getTotalOrderAmount()) + WON);
    }
    public static void printGift(Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        Discount champagneDiscount = Discount.CHAMPAGNE_DISCOUNT;
        String formattedDiscount = NONE;
        System.out.println(PRIVILEGE_MENU);
        if (Calculator.calculateChampagne(payment)) {
            System.out.println(formattedDiscount = decimalFormat.format(-champagneDiscount.getValue()) + WON);
        }
        System.out.println(formattedDiscount);
    }
    public static void printBenefit(int visitDate, Map<Menu, Integer> orderMap, Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        System.out.println(BENEFIT);
        String formattedDiscount = NONE;
        if (checkBenefit(payment)) {
            printDiscount(CHRISTMAS, Calculator.calculateChristmasDiscount(visitDate), decimalFormat);
            printDiscount(DAY_DISCOUNT, Calculator.calculateWeekdayDiscount(visitDate, orderMap), decimalFormat);
            printDiscount(SPECIAL_DISCOUNT, Calculator.calculateSpecialDiscount(visitDate), decimalFormat);
            printChampagneDiscount(Calculator.calculateChampagne(payment), decimalFormat);
        } else {
            System.out.println(formattedDiscount);
        }


        System.out.println(TOTAL_BENEFIT);
        int totalDiscount = payment.getTotalDiscount();
        int totalBenefit = totalDiscount + getChampagneDiscountValue(payment);
        System.out.println(decimalFormat.format(-Math.max(totalBenefit, 0)) + WON);
    }

    public static boolean checkBenefit(Payment payment) {
        return payment.getTotalDiscount() > 0;
    }

    private static void printDiscount(String label, int discount, DecimalFormat decimalFormat) {
        String formattedDiscount = NONE;
        if (discount == 0) {
            System.out.println(label + DOT_SPACE + formattedDiscount);
        }
        formattedDiscount = decimalFormat.format(-discount) + WON;
        System.out.println(label + DOT_SPACE + formattedDiscount);
    }
    private static void printChampagneDiscount(boolean hasChampagne, DecimalFormat decimalFormat) {
        Discount champagneDiscount = Discount.CHAMPAGNE_DISCOUNT;
        String formattedChampagneDiscount = NONE;
        if (hasChampagne) {
            formattedChampagneDiscount = decimalFormat.format(-champagneDiscount.getValue()) + WON;
        }
        System.out.println(BONUS + formattedChampagneDiscount);
    }

    private static int getChampagneDiscountValue(Payment payment) {
        int champagneValue = 0;
        if (Calculator.calculateChampagne(payment)) {
            champagneValue = Discount.CHAMPAGNE_DISCOUNT.getValue();
        }
        return champagneValue;
    }
}
