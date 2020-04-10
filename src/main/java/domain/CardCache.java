package domain;

import java.util.stream.Stream;

public enum CardCache {
    CARD(1),
    CACHE(2);

    private final int value;

    CardCache(final int value) {
        this.value = value;
    }

    public static CardCache of(int input) {
        return Stream.of(CardCache.values())
                .filter(cc -> cc.value == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
