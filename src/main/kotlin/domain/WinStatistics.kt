package domain

import domain.lotto.LottoBundle
import util.LOTTO_PRICE

class WinStatistics(winningNumbers: WinningNumbers, purchasedLottoBundle: LottoBundle) {
    val rankCount: Map<Rank, Int>
    private val spentMoney: Money = Money(purchasedLottoBundle.size * LOTTO_PRICE)
    val earningRate: Double

    init {
        rankCount = BASE_MAP + purchasedLottoBundle
            .compareWithWinningNumbers(winningNumbers)
            .groupingBy { it }
            .eachCount()

        earningRate = calculateEarningRate(spentMoney)
    }

    private fun getTotalIncome(): Money {
        return Money(
            rankCount
                .map { it.key.winningMoney * it.value.toLong() }
                .sum(),
        )
    }

    private fun calculateEarningRate(spentMoney: Money): Double {
        return (getTotalIncome() / spentMoney).toDouble()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WinStatistics

        if (rankCount != other.rankCount) return false

        return true
    }

    override fun hashCode(): Int {
        return rankCount.hashCode()
    }

    companion object {
        private val BASE_MAP: Map<Rank, Int> = Rank.validValues().associateWith { 0 }
    }
}
