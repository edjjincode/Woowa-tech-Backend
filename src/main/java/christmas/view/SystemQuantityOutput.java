package christmas.view;

import java.text.DecimalFormat;
import java.util.Map;

import christmas.controller.ChristmasController;
import christmas.domain.Calculator;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.User;
public class SystemQuantityOutput {
    public static void printQuantity(User user) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<Menu, Integer> entry : user.getOrderMap().entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(menu.getName() + " " + quantity + "개");
        }
    }

    public static void printTotalQuantity(User user) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(user.getTotalOrderAmount()) + "원");
    }
    public static void printBenefit(User user) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("\n<혜택 내역>");

        printDiscount("크리스마스 디데이 할인", Calculator.calculateChristmasDiscount(user.getVisitDate()), decimalFormat);
        printDiscount("평일 할인", Calculator.calculateWeekdayDiscount(user.getVisitDate(), user.getOrderMap()), decimalFormat);
        printDiscount("특별 할인", Calculator.calculateSpecialDiscount(user.getVisitDate()), decimalFormat);
        printChampagneDiscount(Calculator.calculateChampagne(user), decimalFormat);

        System.out.println("\n<총혜택 금액>");
        int totalDiscount = user.getTotalDiscount();
        int totalBenefit = totalDiscount + getChampagneDiscountValue(user);
        System.out.println(decimalFormat.format(-Math.max(totalBenefit, 0)) + "원");
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

    private static int getChampagneDiscountValue(User user) {
        int champagneValue = 0;
        if (Calculator.calculateChampagne(user)) {
            champagneValue = Discount.CHAMPAGNE_DISCOUNT.getValue();
        }
        return champagneValue;
    }
}
