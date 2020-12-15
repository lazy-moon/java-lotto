package com.nextstep.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoSellerTest {
    @DisplayName("로또 개수 계산")
    @Test
    void count() {
        int count = LottoSeller.count(10000);
        assertThat(count).isEqualTo(10);
    }

    @DisplayName("로또 구입")
    @Test
    void buy() {
        Lottos lottos = LottoSeller.buy(10000);
        assertThat(lottos.count()).isEqualTo(10);
    }
}
