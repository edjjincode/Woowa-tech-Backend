package christmas.view;

import java.text.DecimalFormat;
import java.util.Map;

import christmas.domain.*;

public class SystemQuantityOutput {
    public static void printQuantity(Map<Menu, Integer> orderMap) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(menu.getName() + " " + quantity + "개");
        }
    }

    public static void printTotalQuantity(Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(payment.getTotalOrderAmount()) + "원");
    }
    public static void printGift(Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        Discount champagneDiscount = Discount.CHAMPAGNE_DISCOUNT;
        String formattedDiscount = "없음";
        System.out.println("\n<증정 메뉴>");
        if (Calculator.calculateChampagne(payment)) {
            System.out.println(formattedDiscount = decimalFormat.format(-champagneDiscount.getValue()) + "원");
        }
        System.out.println(formattedDiscount);
    }
    public static void printBenefit(int visitDate, Map<Menu, Integer> orderMap, Payment payment) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("\n<혜택 내역>");
        String formattedDiscount = "없음";
        if (checkBenefit(payment)) {
            printDiscount("크리스마스 디데이 할인", Calculator.calculateChristmasDiscount(visitDate), decimalFormat);
            printDiscount("평일 할인", Calculator.calculateWeekdayDiscount(visitDate, orderMap), decimalFormat);
            printDiscount("특별 할인", Calculator.calculateSpecialDiscount(visitDate), decimalFormat);
            printChampagneDiscount(Calculator.calculateChampagne(payment), decimalFormat);
        } else {
            System.out.println(formattedDiscount);
        }


        System.out.println("\n<총혜택 금액>");
        int totalDiscount = payment.getTotalDiscount();
        int totalBenefit = totalDiscount + getChampagneDiscountValue(payment);
        System.out.println(decimalFormat.format(-Math.max(totalBenefit, 0)) + "원");
    }

    public static boolean checkBenefit(Payment payment) {
        return payment.getTotalDiscount() > 0;
    }

    private static void printDiscount(String label, int discount, DecimalFormat decimalFormat) {
        String formattedDiscount = "없음";
        if (discount == 0) {
            System.out.println(label + ": " + formattedDiscount);
        }
        formattedDiscount = decimalFormat.format(-discount) + "원";
        System.out.println(label + ": " + formattedDiscount);
    }
    private static void printChampagneDiscount(boolean hasChampagne, DecimalFormat decimalFormat) {
        Discount champagneDiscount = Discount.CHAMPAGNE_DISCOUNT;
        String formattedChampagneDiscount = "없음";
        if (hasChampagne) {
            formattedChampagneDiscount = decimalFormat.format(-champagneDiscount.getValue()) + "원";
        }
        System.out.println("증정 할인: " + formattedChampagneDiscount);
    }

    private static int getChampagneDiscountValue(Payment payment) {
        int champagneValue = 0;
        if (Calculator.calculateChampagne(payment)) {
            champagneValue = Discount.CHAMPAGNE_DISCOUNT.getValue();
        }
        return champagneValue;
    }
}
