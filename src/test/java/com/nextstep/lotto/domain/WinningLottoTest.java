package com.nextstep.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class WinningLottoTest {

    @DisplayName("보너스 넘버가 6개 숫자에 포함 될 경우 생성자 예외처리")
    @Test
    void containsBonusNumber() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(list, 1));
    }

    @DisplayName("보너스 넘버 체크")
    @Test
    void checkBonusNumber() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        WinningLotto winningLotto = new WinningLotto(list, 7);
        List<LottoNumber> checkList = Stream.of(1,2,3,4,5,7).map(LottoNumber::new).collect(Collectors.toList());
        assertThat(winningLotto.checkBonusNumber(checkList)).isTrue();
    }

}
