package domain

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000) {
        override fun checkSame(countOfMatch: Int, matchBonus: Boolean): Boolean {
            return super.checkSame(countOfMatch, matchBonus) and matchBonus
        }
    },
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0) {
        override fun checkSame(countOfMatch: Int, matchBonus: Boolean): Boolean {
            return false
        }
    }, ;

    open fun checkSame(countOfMatch: Int, matchBonus: Boolean): Boolean {
        return this.countOfMatch == countOfMatch
    }

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return values().find {
                it.checkSame(countOfMatch, matchBonus)
            } ?: MISS
        }

        fun validValues(): List<Rank> {
            return values().filter { it != MISS }
        }
    }
}
