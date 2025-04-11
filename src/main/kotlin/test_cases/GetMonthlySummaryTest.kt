package test_cases

import TransactionManager
import model.Transaction
import model.TransactionType
import storage.TransactionStorage
import java.time.LocalDate

fun main() {
    val dummyStorage = object : TransactionStorage {
        private val transactions = listOf(
            Transaction(
                id = "1",
                amount = 265.5,
                type = TransactionType.EXPENSE,
                category = "Food",
                date = LocalDate.parse("2025-12-10"),
            ),
            Transaction(
                id = "2",
                amount = 265.5,
                type = TransactionType.EXPENSE,
                category = "Food",
                date = LocalDate.parse("2025-07-13"),
            ),
            Transaction(
                id = "3",
                amount = 265.5,
                type = TransactionType.EXPENSE,
                category = "Food",
                date = LocalDate.parse("2025-04-10"),
            ),
            Transaction(
                id = "4",
                amount = 2654.5,
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse("2025-07-12"),
            ),
            Transaction(
                id = "5",
                amount = 265.5,
                type = TransactionType.EXPENSE,
                category = "Food",
                date = LocalDate.parse("2025-12-22"),
            ),
        )

        override fun save(transaction: Transaction) {
        }

        override fun edit(transaction: Transaction): Boolean {
            return false
        }

        override fun delete(transactionId: String): Boolean {
            return false
        }

        override fun load(): List<Transaction> {
            return transactions
        }
    }

    val transactionManager = TransactionManager(dummyStorage)


    println(transactionManager.getMonthlySummary())

    checkEquals(
        name = "When given a list of transactions that's around 3 months, the summaries list should be of size 3",
        result = transactionManager.getMonthlySummary().size,
        correctResult = 3,
    )
}

private fun checkEquals(name: String, result: Int, correctResult: Int) {
    if (result == correctResult) {
        println("Passed - $name")
    } else {
        println("\u001B[31mFailed - $name\u001B[0m")
    }
}

