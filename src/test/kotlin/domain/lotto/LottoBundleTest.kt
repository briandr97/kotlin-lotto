package domain.lotto

import domain.Rank
import domain.Rank.FIRST
import domain.Rank.FOURTH
import domain.Rank.SECOND
import domain.Rank.THIRD
import domain.WinningNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoBundleTest {

    val lottos = listOf<Lotto>(
        Lotto(1, 2, 3, 4, 5, 6),
        Lotto(1, 2, 3, 4, 5, 7),
        Lotto(1, 2, 3, 4, 5, 7),
        Lotto(1, 2, 3, 4, 5, 8),
        Lotto(1, 2, 3, 4, 8, 9),

    )

    @Test
    fun compareWithWinningNumbers() {
        // given
        val winningNumbers = WinningNumbers(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7))
        val lottoBundle = LottoBundle(lottos)
        val expected: List<Rank> = listOf(FIRST, SECOND, SECOND, THIRD, FOURTH)

        // when
        val actual = lottoBundle.compareWithWinningNumbers(winningNumbers)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
