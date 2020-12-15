package com.nextstep.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {
    @DisplayName("로또 숫자가 6개가 아닐 경우 예외처리")
    @Test
    void lotto() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(toLottoNumbers(1,2,3,4,5)));
    }

    @DisplayName("로또 당첨 확인")
    @ParameterizedTest
    @MethodSource
    void getWinning(Lotto lotto, LottoRank expected) {
        WinningLotto winningLotto = new WinningLotto(toLottoNumbers(1,2,3,4,5,6), 7);
        LottoRank lottoRank = lotto.match(winningLotto);
        assertThat(lottoRank).isEqualTo(expected);
    }

    private static Stream<Arguments> getWinning() {
        return Stream.of(
                Arguments.of(new Lotto(toLottoNumbers(1,2,3,4,5,6)), LottoRank.WIN_1ST),
                Arguments.of(new Lotto(toLottoNumbers(1,2,3,4,5,7)), LottoRank.WIN_2ND),
                Arguments.of(new Lotto(toLottoNumbers(11,2,3,4,5,6)), LottoRank.WIN_3RD),
                Arguments.of(new Lotto(toLottoNumbers(11,12,3,4,5,6)), LottoRank.WIN_4TH),
                Arguments.of(new Lotto(toLottoNumbers(11,12,13,4,5,6)), LottoRank.WIN_5TH),
                Arguments.of(new Lotto(toLottoNumbers(11,12,13,14,5,6)), LottoRank.RETIRE)
        );
    }

    @DisplayName("숫자 포함 여부")
    @ParameterizedTest
    @CsvSource({"1,true", "7,false"})
    void contains(int number, boolean expected) {
        Lotto lotto = new Lotto(toLottoNumbers(1,2,3,4,5,6));
        LottoNumber lottoNumber = LottoNumber.of(number);
        assertThat(lotto.contains(lottoNumber)).isEqualTo(expected);
    }

    private static List<LottoNumber> toLottoNumbers(int ... numbers) {
        return IntStream.of(numbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }
}