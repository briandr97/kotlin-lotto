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
    fun `로또 번들을 당첨 번호와 비교하여 알맞는 Rank 리스트가 나오는지 테스트`() {
        // given
        val winningNumbers = WinningNumbers(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7))
        val lottoBundle = LottoBundle(lottos)
        val expected: List<Rank> = listOf(FIRST, SECOND, SECOND, THIRD, FOURTH)

        // when
        val actual = lottoBundle.compareWithWinningNumbers(winningNumbers)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `LottoBundle을 합치는 plus 연산자 함수가 잘 작동하는지 테스트`() {
        // given
        val baseLottoBundle = LottoBundle(lottos)
        val beforeSize = baseLottoBundle.size
        val lottoBundleToAdd = LottoBundle(listOf(Lotto(10, 11, 12, 13, 14, 15)))
        val addSize: Int = lottoBundleToAdd.size

        // when
        val totalLottoBundle = baseLottoBundle + lottoBundleToAdd
        val actual = totalLottoBundle.size
        // then
        assertThat(actual).isEqualTo(beforeSize + addSize)
    }
}
