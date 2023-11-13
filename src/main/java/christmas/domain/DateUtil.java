package christmas.domain;

import java.util.Calendar;
public class DateUtil {
    public static boolean isWeekday(int visitDate) {
        if (visitDate >= 1 && visitDate <= 30) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, visitDate);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek != Calendar.FRIDAY && dayOfWeek != Calendar.SATURDAY;
        } else {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static boolean isWeekend(int visitDate) {
        return !isWeekday(visitDate);
    }

    public static boolean isSpecialEvent(int visitDate) {
        if (visitDate >= 1 && visitDate <= 30) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, visitDate);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek == Calendar.SUNDAY || dayOfWeek == 25;
        } else {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
