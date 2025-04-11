package test_cases

import TransactionManager
import model.Transaction
import model.TransactionType
import model.TransactionValidationErrors
import storage.InMemoryTransactionStorage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

fun main() {
    val date = "2025-04-09"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val transactionManager = TransactionManager(InMemoryTransactionStorage())

    check(
        name = "when have transaction with amount greater than 0, " +
                "type is INCOME or EXPENSE" +
                "category is not empty" +
                "and date in valid format, it should return true",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse(date, formatter)
            )
        )

        ,
        correctResult = listOf()
    )

    check(
        name = "when have transaction with amount smaller than 1, it should return false",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 0.0,
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = listOf(TransactionValidationErrors.INVALID_AMOUNT_RANGE.message)
    )

    check(
        name = "when have transaction with amount not a number, it should return false",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = ("").toDoubleOrNull(),
                type = TransactionType.INCOME,
                category = "Food",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = listOf(TransactionValidationErrors.INVALID_AMOUNT_NOT_NUMBER.message)
    )

    check(
        name = "when have transaction with empty category, it should return false",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = listOf(TransactionValidationErrors.INVALID_CATEGORY.message)
    )

    check(
        name = "when have transaction with category contain spaces only, it should return false",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID().toString(),
                amount = 5000.0,
                type = TransactionType.INCOME,
                category = "       ",
                date = LocalDate.parse(date, formatter)
            )
        ),
        correctResult = listOf(TransactionValidationErrors.INVALID_CATEGORY.message)
    )

}






fun check(name: String, result: List<String>, correctResult: List<String>) {
    if(result == correctResult) {
        println("Passed- $name")
    } else {
        println("Failed- $name")
    }
}