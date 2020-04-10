package view;

import domain.menu.Menu;
import domain.order.Order;
import domain.possmachine.PossMachine;
import domain.table.Table;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_FORMAT = "└ %s ┘";
    private static final String ORDERED = "₩";
    private static final String NOT_ORDERED = "─";

    public static void printMainMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("##메인화면\n");
        stringBuilder.append("1 - 주문등록\n");
        stringBuilder.append("2 - 결제하기\n");
        stringBuilder.append("3 - 프로그램 종료\n");

        System.out.println(stringBuilder.toString());
    }

    public static void printTables(final List<Table> tables, final PossMachine possMachine) {
        final int size = tables.size();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("## 테이블 목록\n");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(TOP_LINE);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < size; i++) {
            String table = String.format(TABLE_FORMAT, tables.get(i));
            stringBuilder.append(table);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < size; i++) {
            String bottom = String.format(BOTTOM_FORMAT, parseOrderExist(tables.get(i), possMachine));
            stringBuilder.append(bottom);
        }
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    private static String parseOrderExist(Table table, PossMachine possMachine) {
        if (possMachine.isOrdered(table)) {
            return ORDERED;
        }
        return NOT_ORDERED;
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    public static void printOrders(final List<Order> orders) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("## 주문 내역\n");
        stringBuilder.append("메뉴 수량 금액\n");
        for (Order order : orders) {
            Menu menu = order.getMenu();
            String info = String.format("%s %d %d", menu.getName(), order.getAmount(), menu.getPrice());
            stringBuilder.append(info);
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    public static void printPrice(final int price) {
        System.out.println("## 최종 결제할 금액");
        System.out.println(price);
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }
}
