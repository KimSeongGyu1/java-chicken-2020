package domain.order;

import domain.menu.Menu;
import domain.order.discountstrategy.DiscountStrategy;
import domain.table.Table;

public class Order {
    private final int tableNumber;
    private final int menuNumber;
    private final int amount;

    public Order(int tableNumber, int menuNumber, int amount) {
        this.tableNumber = tableNumber;
        this.menuNumber = menuNumber;
        this.amount = amount;
    }

    public boolean isOrderOf(Table table) {
        return table.isTableOf(tableNumber);
    }

    public boolean isChickenOrder() {
        return Menu.of(menuNumber).isChickenMenu();
    }

    public int roundedAmount(final int roundingValue) {
        return amount / roundingValue;
    }

    public int calculatePrice(DiscountStrategy discountStrategy) {
        double discountAmount = discountStrategy.calculateDiscountAmount(this);
        double discountRatio = discountStrategy.calculateDiscountRatio();

        return (int)((calculateDefaultPrice() - discountAmount) * discountRatio);
    }

    private int calculateDefaultPrice() {
        return Menu.of(menuNumber).calculateManyPrice(amount);
    }
}
