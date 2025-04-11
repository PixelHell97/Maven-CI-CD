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
                date = LocalDate.parse("2025-07-12"),
            ),
            Transaction(
                id = "3",
                amount = 265.5,
                type = TransactionType.EXPENSE,
                category = "Food",
                date = LocalDate.parse("2025-04-10"),
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

    checkEquals(
        name = "When calling TransactionManager#getAllTransactions and storage have 3 items, It should return list of 3 items",
        result = transactionManager.getAll().size,
        correctResult = 3,
    )
}

private fun checkEquals(
    name: String,
    result: Int,
    correctResult: Int,
) {
    if (result == correctResult) {
        println("Passed - $name")
    } else {
        println("Failed - $name")
    }
}