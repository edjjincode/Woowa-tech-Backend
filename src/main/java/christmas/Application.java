package christmas;

import christmas.controller.ChristmasController;
import christmas.domain.User;

public class Application {
    public static void main(String[] args) {
        User user = new User();
        ChristmasController christmasController = new ChristmasController(user);
        christmasController.run();
    }
}
