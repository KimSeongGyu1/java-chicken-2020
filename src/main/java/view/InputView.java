package view;

import domain.table.Table;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputCommandNumber() {
        System.out.println("## 원하는 기능을 선택하세요.");
        String input = scanner.nextLine().trim();
        validateNumberInput(input);

        return Integer.parseInt(input);
    }

    public static int inputTableNumber() {
        System.out.println("## 테이블을 선택하세요.");
        String input = scanner.nextLine().trim();
        validateNumberInput(input);

        return Integer.parseInt(input);
    }

    public static int inputMenuNumber() {
        System.out.println("## 주문할 메뉴를 선택하세요.");
        String input = scanner.nextLine().trim();
        validateNumberInput(input);

        return Integer.parseInt(input);
    }

    public static int inputAmount() {
        System.out.println("## 주문할 수량을 선택하세요.");
        String input = scanner.nextLine().trim();
        validateNumberInput(input);

        return Integer.parseInt(input);
    }

    public static int inputCardOrCache(Table table) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("## %s번 테이블의 결제를 진행합니다.\n", table));
        stringBuilder.append("## 신용카드는 1번, 현금은 2번.\n");
        System.out.println(stringBuilder.toString());

        String input = scanner.nextLine().trim();
        validateNumberInput(input);

        return Integer.parseInt(input);
    }

    private static void validateNumberInput(String input) {
        if (isNumberFormat(input) || input.isEmpty()) {
            throw new IllegalArgumentException("적절한 숫자를 입력해주세요");
        }
    }

    private static boolean isNumberFormat(String input) {
        return input.chars()
                .anyMatch(ch -> !Character.isDigit(ch));
    }
}
