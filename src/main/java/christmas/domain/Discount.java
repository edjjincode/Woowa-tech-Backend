package christmas.domain;
public enum Discount {
    CHRISTMAS_DISCOUNT(100),
    WEEKDAY_DISCOUNT(2_023),
    WEEKEND_DISCOUNT(2_023),

    SPECIAL_DISCOUNT(1_000);

    private int value;
    Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
