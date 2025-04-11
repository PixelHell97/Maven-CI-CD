package test_cases

import TransactionManager
import model.Transaction
import model.TransactionType
import storage.TransactionStorage
import java.time.LocalDate

fun main(){
    val dummyStorage = object: TransactionStorage{
        private val transactions = mutableListOf<Transaction>(
            Transaction(
                id = "123456",
                amount = 1500.0,
                date = LocalDate.now(),
                type = TransactionType.INCOME,
                category = "Food"
            )
        )
        override fun save(transaction: Transaction) {
            //do nothing
        }

        override fun edit(transaction: Transaction): Boolean {
            //do nothing
            return false
        }

        override fun delete(transactionId: String): Boolean {
            return transactions.removeIf { it.id == transactionId }
        }

        override fun load(): List<Transaction> {
            //do nothing
            return emptyList()
        }
    }
    val transactionManager = TransactionManager(dummyStorage)
    check(
        title = "When id is blank, should return false",
        result = transactionManager.delete(""),
        expectedResult = false
    )
    check(
        title = "When id doesn't exist, should return false",
        result = transactionManager.delete("456789"),
        expectedResult = false
    )
    check(
        title = "When id exists, should return true",
        result = transactionManager.delete("123456"),
        expectedResult = true
    )
}

private fun<T> check(
    title: String,
    result: T,
    expectedResult: T
) {
    println(
        if(result == expectedResult){
            "Passed: $title"
        } else {
            "Failed: $title"
        }
    )
}