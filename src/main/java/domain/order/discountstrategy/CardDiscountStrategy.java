package domain.order.discountstrategy;

import domain.order.Order;

public class CardDiscountStrategy implements DiscountStrategy {
    private static final double CARD_DISCOUNT_RATIO = 1;

    @Override
    public double calculateDiscountAmount(Order order) {
        if (order.isChickenOrder()) {
            return CHICKEN_DISCOUNT * order.roundedAmount(CHICKEN_DISCOUNT_THRESHOLD);
        }
        return NO_DISCOUNT;
    }

    @Override
    public double calculateDiscountRatio() {
        return CARD_DISCOUNT_RATIO;
    }
}
