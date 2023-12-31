package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum MenuType {
    APPETIZER("애피타이저", Arrays.asList(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(Menu.T_BONE_STEAK, Menu.BBQ_RIB, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCO_CAKE, Menu.ICECREAM)),
    DRINK("음료", Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE));

    private String menu;
    private List<Menu> foodList;

    MenuType(String menu, List<Menu> foodList) {
        this.menu = menu;
        this.foodList = foodList;
    }

    public List<Menu> getFoodList() {
        return foodList;
    }
}
