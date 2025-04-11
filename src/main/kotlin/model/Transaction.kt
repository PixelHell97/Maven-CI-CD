package model

import kotlinx.serialization.Serializable
import main.java.utils.LocalDateSerializer
import java.time.LocalDate

@Serializable
data class Transaction(
    val id: String,
    val amount: Double?,
    val type: TransactionType,
    val category: String,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate = LocalDate.now()) {
    fun validateTransaction (): List<String> {
        val errors = mutableListOf<String>()

        if (amount.toString().isBlank() || amount == null)
            errors.add(TransactionValidationErrors.INVALID_AMOUNT_NOT_NUMBER.message)

        else if(amount <= 0) errors.add(TransactionValidationErrors.INVALID_AMOUNT_RANGE.message)

        if(type == TransactionType.INVALID) errors.add(TransactionValidationErrors.INVALID_TYPE.message)

        if(category.isBlank())
            errors.add(TransactionValidationErrors.INVALID_CATEGORY.message)

        return errors

    }
}



