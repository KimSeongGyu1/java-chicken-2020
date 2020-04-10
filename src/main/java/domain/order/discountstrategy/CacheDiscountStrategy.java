package domain.order.discountstrategy;

import domain.order.Order;

public class CacheDiscountStrategy implements DiscountStrategy {
    private static final double CACHE_DISCOUNT_RATIO = 0.95;

    @Override
    public double calculateDiscountAmount(Order order) {
        if (order.isChickenOrder()) {
            return CHICKEN_DISCOUNT * order.roundedAmount(CHICKEN_DISCOUNT_THRESHOLD);
        }
        return NO_DISCOUNT;
    }

    @Override
    public double calculateDiscountRatio() {
        return CACHE_DISCOUNT_RATIO;
    }
}
