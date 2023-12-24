package christmas.domain;

public enum Badge {
    산타(20_000),
    트리(10_000),
    별(5_000),
    없음(0);

    private final int threshold;

    Badge(int threshold) {
        this.threshold = threshold;
    }

    public static String getBadgeByTotalDiscount(int totalBenefit) {
        for (Badge badge : Badge.values()) {
            if (totalBenefit >= badge.threshold) {
                return badge.name();
            }
        }
        return 없음.name();
    }
}

