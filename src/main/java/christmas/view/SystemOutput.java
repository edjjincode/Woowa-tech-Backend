package christmas.view;

import java.text.DecimalFormat;

import christmas.controller.ChristmasController;
import christmas.domain.Calculator;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.User;

import java.util.Map;

public class SystemOutput {

    public static void printDate(User user) {
        System.out.println("\n12월" + user.getVisitDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public static void printOrderDetails(User user) {

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.println("<주문 메뉴>");
        for (Map.Entry<Menu, Integer> entry : user.getOrderMap().entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(menu.getName() + " " + quantity + "개");
        }

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(user.getTotalOrderAmount()) + "원");

        System.out.println("\n<증정 메뉴>");
        if (user.getTotalDiscount() > 120000) {
            System.out.println("샴페인 1개");
        } else {
            System.out.println("없음");
        }

        System.out.println("\n<혜택 내역>");
        if (user.getTotalDiscount() > 0) {
            System.out.println("크리스마스 디데이 할인: " + decimalFormat.format(Calculator.calculateChristmasDiscount(user.getVisitDate())*(-1)) + "원");
            System.out.println("평일 할인: " + decimalFormat.format(Calculator.calculateWeekdayDiscount(user.getVisitDate(), user.getOrderMap())*(-1)) + "원");
            System.out.println("특별 할인: " + decimalFormat.format(Calculator.calculateSpecialDiscount(user.getVisitDate())*(-1)) + "원");
            if (ChristmasController.calculateChampagne(user)){
                System.out.println("증정 할인: " + decimalFormat.format(Discount.CHAMPAGNE_DISCOUNT.getValue()*(-1)) + "원");
            }
            else {
                System.out.println("증정 할인: " + "없음");
            }
        } else {
            System.out.println("없음");
        }

        System.out.println("\n<총혜택 금액>");
        if (ChristmasController.calculateChampagne(user)) {
            System.out.println(decimalFormat.format((user.getTotalDiscount() + Discount.CHAMPAGNE_DISCOUNT.getValue())*(-1)) + "원");
        }
        else {
            System.out.println(decimalFormat.format(user.getTotalDiscount()*(-1)) + "원");
        }


        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(decimalFormat.format(user.getFinalPayment()) + "원");

        System.out.println("\n<12월 이벤트 배지>");
        if (user.getTotalDiscount() > 0) {
            System.out.println(getBadgeByTotalDiscount(user.getTotalDiscount()));
        } else {
            System.out.println("없음");
        }
    }
    private static String getBadgeByTotalDiscount(int totalDiscount) {
        if (totalDiscount >= 20_000) {
            return "산타";
        } else if (totalDiscount >= 10_000) {
            return "트리";
        } else if (totalDiscount >= 5_000) {
            return "별";
        } else {
            return "없음";
        }
    }


}
