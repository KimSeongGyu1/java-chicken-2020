package domain.menu;

public class Menu {
    private final int number;
    private final String name;
    private final Category category;
    private final int price;

    public Menu(final int number, final String name, final Category category, final int price) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public static Menu of(final int number) {
        return MenuRepository.menus()
                .stream()
                .filter(menu -> menu.isMenuOf(number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 번호입니다."));
    }

    public boolean isMenuOf(final int number) {
        return this.number == number;
    }

    public boolean isChickenMenu() {
        return category == Category.CHICKEN;
    }

    public int calculateManyPrice(final int amount) {
        return price * amount;
    }

    @Override
    public String toString() {
        return category + " " + number + " - " + name + " : " + price + "원";
    }
}
