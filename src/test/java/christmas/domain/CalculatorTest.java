package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import christmas.domain.Calculator;

import java.util.HashMap;
import java.util.Map;

class CalculatorTest {
    @DisplayName("calculateChristmasDiscount제대로 작동하는지 확인하기")
    @Test
    void checkCalculateChristmasDiscount() {
        assertEquals(0, Calculator.calculateChristmasDiscount(0));  // Before December 1st
        assertEquals(0, Calculator.calculateChristmasDiscount(26));
        assertEquals(1_000, Calculator.calculateChristmasDiscount(1));  // December 1st
        assertEquals(1_400, Calculator.calculateChristmasDiscount(5));
    }

    @DisplayName("calculateWeekdayDiscount제대로 작동하는지 확인하기")
    @Test
    void checkCalculateWeekdayDiscount() {
        Map<Menu, Integer> orderMap = new HashMap<>();
        orderMap.put(Menu.T_BONE_STEAK, 1);
        orderMap.put(Menu.CAESAR_SALAD, 1);
        int visitDate = 1;
        int expected = Calculator.calculateWeekdayDiscount(visitDate, orderMap);
        assertEquals(expected, 2_023);
    }

    @DisplayName("calculateSpecialDiscount 제대로 작동하는지 확인하기")
    @Test
    void checkCalculateSpecialDiscount() {
        Map<Menu, Integer> orderMap = new HashMap<>();
        orderMap.put(Menu.T_BONE_STEAK, 1);
        orderMap.put(Menu.CAESAR_SALAD, 1);
        int visitDate = 3;
        int expected = Calculator.calculateSpecialDiscount(visitDate, orderMap);
        assertEquals(expected, 2_000);
    }
    @Test
    void checkCalculateSpecialDiscount1() {
        int visitDate = 3;
        boolean isSpecial = DateUtil.isSpecialEvent(visitDate);
        System.out.println(isSpecial);
    }


}