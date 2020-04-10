package domain;

import java.util.stream.Stream;

public enum Command {
    ORDER(1),
    PAY(2),
    TERMINATE(3);

    private final int value;

    Command(final int value) {
        this.value = value;
    }

    public static Command of(int input) {
        return Stream.of(Command.values())
                .filter(command -> command.value == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }
}
