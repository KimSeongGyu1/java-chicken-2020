package domain.order.discountstrategy;

import domain.order.Order;

public interface DiscountStrategy {
    int NO_DISCOUNT = 0;
    int CHICKEN_DISCOUNT = 10000;
    int CHICKEN_DISCOUNT_THRESHOLD = 10;

    double calculateDiscountAmount(Order order);

    double calculateDiscountRatio();
}
