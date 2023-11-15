package christmas;

import christmas.controller.ChristmasController;
import christmas.domain.Payment;
import christmas.domain.User;

public class Application {
    public static void main(String[] args) {
        Payment payment = new Payment();
        User user = new User(payment);
        ChristmasController christmasController = new ChristmasController(user);
        christmasController.run();
    }
}
