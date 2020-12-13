package com.nextstep.lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        if ( numbers == null ) {
            throw new IllegalArgumentException("number is not allow null");
        }

        if ( numbers.size() != LOTTO_NUMBER_COUNT ) {
            throw new IllegalArgumentException("number count shoud be " + LOTTO_NUMBER_COUNT);
        }
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public LottoWinning getWinning(Lotto winningLotto) {
        long matchedCount = getMatchedCount(winningLotto);
        return LottoWinning.select(matchedCount);
    }

    private long getMatchedCount(Lotto winningLotto) {
        return this.numbers.stream()
                .filter(winningLotto.numbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
