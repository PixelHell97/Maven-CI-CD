package model

enum class TransactionValidationErrors(val message: String) {
    INVALID_AMOUNT_RANGE("Amount must be greater than 0"),
    INVALID_AMOUNT_NOT_NUMBER("Amount is not a number"),
    INVALID_TYPE("You must select a correct transaction type 1 or 2"),
    INVALID_CATEGORY("Category cannot be empty or blank")
}